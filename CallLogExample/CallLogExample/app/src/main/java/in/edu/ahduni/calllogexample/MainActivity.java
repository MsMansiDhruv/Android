package in.edu.ahduni.calllogexample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListView.OnItemSelectedListener{

    Spinner spinCalls;
    ListView lstCalls;

    static final int READ_ID = 123;
    ArrayList<String> typeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinCalls = (Spinner) findViewById(R.id.spinCalls);
        lstCalls = (ListView) findViewById(R.id.lstCalls);

        typeList = new ArrayList<>();
        typeList.add("All Calls");
        typeList.add("Incoming Calls");
        typeList.add("Outgoing Calls");
        typeList.add("Missed Calls");
        typeList.add("Rejected Calls");

        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                typeList);
        spinCalls.setAdapter(spinAdapter);

        spinCalls.setOnItemSelectedListener(this);
    }

    boolean askPermission(int requestId, String name) {
        if (Build.VERSION.SDK_INT >= 23) {

            int permission = ActivityCompat
                    .checkSelfPermission(this,name);

            if (permission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{name}, requestId);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0) {
            if (requestCode == READ_ID) {
                if (grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    //viewCallDetails
                    viewCallDetails(0);
                }
            }
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        boolean canRead = askPermission(READ_ID,
                Manifest.permission.READ_CALL_LOG);
        if (canRead)
            viewCallDetails(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        boolean canRead = askPermission(READ_ID,
                Manifest.permission.READ_CALL_LOG);

        if (canRead)
            viewCallDetails(0);
    }

    void viewCallDetails(int spinPosition) {
        int type = 0;
        switch (spinPosition) {
            case 1:
                type = CallLog.Calls.INCOMING_TYPE;
                break;

            case 2:
                type = CallLog.Calls.OUTGOING_TYPE;
                break;

            case 3:
                type = CallLog.Calls.MISSED_TYPE;
                break;

            case 4:
                type = CallLog.Calls.REJECTED_TYPE;
                break;
        }

        CallLogHelper helper = new CallLogHelper(getContentResolver());
        ArrayList<Call> callList = helper.getCallDetails(type);
        CustomCallLog customAdapter = new CustomCallLog(MainActivity.this,
                callList);
        lstCalls.setAdapter(customAdapter);
    }
}
