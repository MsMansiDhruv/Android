package com.example.nemesis.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    Button buttonLogin;
    CheckBox checkBoxRememberMe;

    String uname="admin", pass="admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = (EditText)findViewById(R.id.editTextUsername);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        checkBoxRememberMe = (CheckBox)findViewById(R.id.checkBoxRememberMe);

        final SharedPreferences prefs = getSharedPreferences("RememberMePrefs",MODE_PRIVATE);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if( username.equals(uname) && password.equals(pass)){
                    //Login successful
                    SharedPreferences.Editor editor = prefs.edit();

                    if(checkBoxRememberMe.isChecked()){
                        editor.putString("username",uname);
                        editor.putString("password",pass);
                        editor.commit();    //synchronous
                        editor.apply();     //asynchronous
                        Toast.makeText(LoginActivity.this, "Details Saved", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //Checkbox unchecked
                        if(prefs.contains("username")){
                           // editor.remove("username");
                            editor.clear();
                            editor.apply();
//                            editor.commit();
                            Toast.makeText(LoginActivity.this, "Details removed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();


                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);

                }


            }
        });

    }
}
