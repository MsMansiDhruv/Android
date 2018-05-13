package com.example.nemesis.localnotifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView textViewDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textViewDisplay = (TextView)findViewById(R.id.textViewDisplay);

        textViewDisplay.setText(getIntent().getAction());

    }
}
