package com.example.nemesis.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView textViewMessage;
    Button buttonSettings;
    TextView textViewSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewMessage = (TextView)findViewById(R.id.textViewMessage);
        textViewSettings = (TextView)findViewById(R.id.textViewSettings);
        buttonSettings =(Button)findViewById(R.id.buttonSettings);

        SharedPreferences prefs = getSharedPreferences("RememberMePrefs",MODE_PRIVATE);

        if(prefs.contains("username")){
            textViewMessage.setText("Hello "+prefs.getString("username",""));
        }

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        String gender="male";

        if(sp.getBoolean("gender",true)){
            gender="female";
        }

        textViewSettings.setText(sp.getString("name","") + gender);
    }
}
