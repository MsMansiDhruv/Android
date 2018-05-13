package com.example.nemesis.localnotifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextTitle,editTextMessage;
    Button buttonType1,buttonType2,buttonType3,buttonCustom;

    NotificationHelper notificationHelper;

    String title,message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMessage = (EditText)findViewById(R.id.editTextMessage);
        editTextTitle = (EditText)findViewById(R.id.editTextTitle);

        buttonType1 = (Button)findViewById(R.id.buttonType1);
        buttonType2 = (Button)findViewById(R.id.buttonType2);
        buttonType3 = (Button)findViewById(R.id.buttonType3);
        buttonCustom = (Button)findViewById(R.id.buttonCustom);

        notificationHelper = new NotificationHelper(getApplicationContext());



        buttonType1.setOnClickListener(this);
        buttonType2.setOnClickListener(this);
        buttonType3.setOnClickListener(this);
        buttonCustom.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        title = editTextTitle.getText().toString();
        message = editTextMessage.getText().toString();

        switch (v.getId()){
            case R.id.buttonType1:
                Toast.makeText(this, "SwitchType1" +title+message, Toast.LENGTH_SHORT).show();
                notificationHelper.Type1Notification(title,message);

                break;
            case R.id.buttonType2:
                notificationHelper.Type2Notification(title,message);
                break;
            case R.id.buttonType3:
                notificationHelper.Type3Notification(title,message);
                break;
            case R.id.buttonCustom:
                notificationHelper.CustomNotification(title,message);
                break;
        }

    }
}
