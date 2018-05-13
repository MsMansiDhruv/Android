package in.edu.ahduni.telephonyapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnCallNumber, btnPhoneDetails,
            btnSendEmail, btnSendSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCallNumber = (Button) findViewById(R.id.btnCallNumber);
        btnPhoneDetails = (Button) findViewById(R.id.btnPhoneDetails);
        btnSendEmail = (Button) findViewById(R.id.btnSendEmail);
        btnSendSms = (Button) findViewById(R.id.btnSendSMS);

        btnCallNumber.setOnClickListener(this);
        btnPhoneDetails.setOnClickListener(this);
        btnSendEmail.setOnClickListener(this);
        btnSendSms.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent i = new Intent(MainActivity.this,
                CallNumberActivity.class);

        switch (v.getId()) {

            case R.id.btnPhoneDetails:
                i = new Intent(MainActivity.this,
                        PhoneDetailsActivity.class);
                break;

            case R.id.btnSendEmail:
                i = new Intent(MainActivity.this,
                        SendEmailActivity.class);
                break;

            case R.id.btnSendSMS:
                i = new Intent(MainActivity.this,
                        SendSmsActivity.class);
                break;
        }

        startActivity(i);
    }
}
