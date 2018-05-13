package in.edu.ahduni.telephonyapi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PhoneDetailsActivity extends AppCompatActivity {

    static final int READ_ID = 123;
    TextView txtPhoneDetails;
    PhoneHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_details);

        txtPhoneDetails = (TextView) findViewById(R.id.txtPhoneDetails);

        helper = new PhoneHelper(getApplicationContext());
        Boolean canRead = askPermission(READ_ID,
                Manifest.permission.READ_PHONE_STATE);
        if (canRead) {
            txtPhoneDetails.setText(helper.getPhoneDetails());
        }
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
                    txtPhoneDetails.setText(helper.getPhoneDetails());
                }
            }
        }
    }
}
