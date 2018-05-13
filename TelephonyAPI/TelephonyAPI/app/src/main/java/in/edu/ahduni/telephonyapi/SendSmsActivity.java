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

public class SendSmsActivity extends AppCompatActivity {

    EditText edtPhoneNumber, edtMessage;
    Button btnSendMessage;
    static final int SEND_ID = 123;
    PhoneHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        edtPhoneNumber = (EditText) findViewById(R.id.edtPhone);
        edtMessage = (EditText) findViewById(R.id.edtMessage);
        btnSendMessage = (Button) findViewById(R.id.btnSendMessage);

        helper = new PhoneHelper(getApplicationContext());
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean canSend = askPermission(SEND_ID,
                        Manifest.permission.SEND_SMS);
                if (canSend) {
                    sendingSMS();
                }
            }
        });
    }

    void sendingSMS() {
        SMS sms = new SMS();
        sms.setPhoneNumber(edtPhoneNumber.getText().toString());
        sms.setMessage(edtMessage.getText().toString());
        helper.sendSMS(sms);
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
            if (requestCode == SEND_ID) {
                if (grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    sendingSMS();
                }
            }
        }
    }
}
