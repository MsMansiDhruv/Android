package in.edu.ahduni.localnotificationsexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtTitle, edtMessage;
    Button btnType1, btnType2, btnType3, btnClearNotifications,
            btnCustomNotification, btnInlineTextboxNotification;
    NotificationHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtMessage = (EditText) findViewById(R.id.edtMessage);

        btnType1 = (Button) findViewById(R.id.btnType1);
        btnType2 = (Button) findViewById(R.id.btnType2);
        btnType3 = (Button) findViewById(R.id.btnType3);
        btnClearNotifications = (Button) findViewById(R.id.btnClearNotifications);
        btnCustomNotification = (Button) findViewById(R.id.btnCustomNotification);
        btnInlineTextboxNotification = (Button) findViewById(R.id.btnInlineTextbox);

        btnType1.setOnClickListener(this);
        btnType2.setOnClickListener(this);
        btnType3.setOnClickListener(this);
        btnClearNotifications.setOnClickListener(this);
        btnCustomNotification.setOnClickListener(this);
        btnInlineTextboxNotification.setOnClickListener(this);

        helper = new NotificationHelper(getApplicationContext());
    }

    @Override
    public void onClick(View v) {

        String title = edtTitle.getText().toString();
        String message = edtMessage.getText().toString();

        switch (v.getId()) {
            case R.id.btnType1:
                helper.displayType1Notification(title, message);
                break;

            case R.id.btnType2:
                helper.displayType2Notification(title, message);
                break;

            case R.id.btnType3:
                helper.displayType3Notification(title, message);
                break;

            case R.id.btnClearNotifications:
                helper.clearNotification();
                break;

            case R.id.btnCustomNotification:
                helper.displayCustomNotification(title, message);
                break;

            case R.id.btnInlineTextbox:
                helper.displayInlineTextboxNotification(title, message);
                break;
        }
    }
}
