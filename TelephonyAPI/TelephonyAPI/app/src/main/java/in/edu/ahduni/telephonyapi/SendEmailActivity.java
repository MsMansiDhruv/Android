package in.edu.ahduni.telephonyapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SendEmailActivity extends AppCompatActivity {

    EditText edtTo, edtCC, edtBCC, edtSubject, edtMessage;
    PhoneHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        edtTo = (EditText) findViewById(R.id.edtTo);
        edtCC= (EditText) findViewById(R.id.edtCC);
        edtBCC= (EditText) findViewById(R.id.edtBCC);
        edtSubject= (EditText) findViewById(R.id.edtSubject);
        edtMessage= (EditText) findViewById(R.id.edtMessage);

        helper = new PhoneHelper(getApplicationContext());

        Button btnSendEmail = (Button) findViewById(R.id.btnSendEmail);
        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Email email = new Email();
                email.setTo(edtTo.getText().toString());
                email.setCc(edtCC.getText().toString());
                email.setBcc(edtBCC.getText().toString());
                email.setSubject(edtSubject.getText().toString());
                email.setMessage(edtMessage.getText().toString());

                helper.sendEmail(email);
            }
        });
    }
}
