package in.edu.ahduni.localnotificationsexample;

import android.content.Intent;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtMessage = (TextView) findViewById(R.id.txtMessage);
        txtMessage.setText("You have chosen : " + getIntent().getAction());

        Bundle bundle = RemoteInput.getResultsFromIntent(getIntent());
        String message = "";
        if (bundle != null) {
            message = bundle.getCharSequence("edtReply").toString();
            txtMessage.setText(message);

            NotificationHelper helper = new NotificationHelper(getApplicationContext());
            helper.displayReplyMessageNotification(message);

            //Close Status Bar/Notification Drawer
            Intent intent = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            sendBroadcast(intent);
        }
    }
}
