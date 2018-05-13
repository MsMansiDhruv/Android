package com.example.nemesis.telephonyapi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CallPhoneActivity extends AppCompatActivity {

    EditText editTextCall;
    TextView txt;
    Button buttonCall;

    private static final int ACCESS_ID = 900;

    PhoneHelper helper;

    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_phone);

        editTextCall = (EditText)findViewById(R.id.editTextPhone);
        buttonCall = (Button)findViewById(R.id.buttonCall);
        txt = (TextView)findViewById(R.id.txt);

        helper = new PhoneHelper(getApplicationContext());
        number = editTextCall.getText().toString();

        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean canAccess = askPermission(ACCESS_ID,
                        Manifest.permission.READ_PHONE_STATE);
                if (canAccess) {
                    //helper.CallPhone(number);
                    txt.setText(helper.PhoneDetails());
                    //Toast.makeText(CallPhoneActivity.this, "OnCLick", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean askPermission(int requestId, String name) {
        if (Build.VERSION.SDK_INT >= 23) {
            int permission = ActivityCompat.checkSelfPermission(this, name);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {name}, requestId);
                return false;
            }
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions,
                grantResults);
        if (grantResults.length > 0) {
            switch (requestCode) {
                case ACCESS_ID:
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        //helper.CallPhone(number);
                        txt.setText(helper.PhoneDetails());
                       // Toast.makeText(this, "On request Permission", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }


}
