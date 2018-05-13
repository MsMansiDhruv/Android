package in.edu.ahduni.telephonyapi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CallNumberActivity extends AppCompatActivity {

    static final int CALL_ID = 123;
    EditText edtCallNumber;
    Button btnCallNumber;
    PhoneHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_number);

        edtCallNumber = (EditText) findViewById(R.id.edtPhoneNumber);
        btnCallNumber = (Button) findViewById(R.id.btnCall);

        helper = new PhoneHelper(getApplicationContext());
        btnCallNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean canCall = askPermission(CALL_ID,
                        Manifest.permission.CALL_PHONE);
                if (canCall)
                    helper.callPhoneNumber(edtCallNumber.getText().toString());
            }
        });
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
            if (requestCode == CALL_ID) {
                if (grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    helper.callPhoneNumber(edtCallNumber.getText().toString());
                }
            }
        }
    }
}
