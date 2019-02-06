package com.example.osilifeconnect_ase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText usernameTextField = findViewById(R.id.usernameTextField);
    private EditText passwordTextField = findViewById(R.id.passwordTextField);

    private Button loginButton = findViewById(R.id.loginButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginMethod(View view){
        if(usernameTextField.getText().toString().equals("Username")) {
            if (passwordTextField.getText().toString().equals("Password")) {
                //TODO: Implement page change activity.
            }
        }
        
        return;
    }

}
