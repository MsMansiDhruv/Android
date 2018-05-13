package in.edu.ahduni.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    CheckBox chkRememberMe;
    Button btnLogin;
    String username = "demo", password = "abc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        chkRememberMe = (CheckBox) findViewById(R.id.chkRememberMe);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        //Private Preference
        //final SharedPreferences prefs = getPreferences(MODE_PRIVATE);

        //SharedPreference
        final SharedPreferences prefs = getSharedPreferences("RememberMePrefs", MODE_PRIVATE);

        if (prefs.contains("username")) {
            edtUsername.setText(prefs.getString("username", ""));
            edtPassword.setText(prefs.getString("password", ""));
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = edtUsername.getText().toString();
                String pass = edtPassword.getText().toString();

                String message = "Invalid Details";
                if(uname.equals(username) && pass.equals(password)) {
                    SharedPreferences.Editor editor = prefs.edit();

                    if (chkRememberMe.isChecked()) {
                        //Add Preferences
                        if (!prefs.contains("username")) {
                            editor.putString("username", uname);
                            editor.putString("password", password);
                            //editor.commit();
                            editor.apply();     //Immediately stores values
                            message = "Details Saved";
                        } else
                            message = "Details Already Exists";

                    } else {
                        //Remove Preferences

                        if (prefs.contains("username")) {
                            editor.remove("username");      //Removes key one by one
                            editor.clear(); //Clear All Preference Values
                            editor.apply();
                            message = "Details Removed";
                        } else
                            message = "Details Doesn't Exist For Removal";
                    }

                    Intent i = new Intent(MainActivity.this,
                            WelcomeActivity.class);
                    startActivity(i);
                }

                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
