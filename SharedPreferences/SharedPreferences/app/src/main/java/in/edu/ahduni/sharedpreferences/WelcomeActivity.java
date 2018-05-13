package in.edu.ahduni.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    TextView txtWelcome, txtMessage;
    Button btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        txtWelcome = (TextView) findViewById(R.id.txtWelcome);

        //Get SharedPreferences
        SharedPreferences prefs = getSharedPreferences
                ("RememberMePrefs", MODE_PRIVATE);
        if (prefs.contains("username"))
            txtWelcome.setText("Welcome, " +
                    prefs.getString("username", ""));

        //Settings Preference
        btnSettings = (Button) findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WelcomeActivity.this,
                        SettingsActivity.class);
                startActivity(i);
            }
        });

        //Settings Preference
        txtMessage = (TextView) findViewById(R.id.txtMessage);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sp = PreferenceManager.
                getDefaultSharedPreferences(getBaseContext());

        if (sp.getBoolean("gender", false)) {
            txtMessage.setText(
                    sp.getString("fullName", "")
                            + ", Female");
        }else {
            txtMessage.setText(
                    sp.getString("fullName", "")
                            + ", Male");
        }
    }
}
