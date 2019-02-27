package com.example.osilifeconnect_ase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText usernameTextField;
    private EditText passwordTextField;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
    }

    /**Cullen messing with stuff!!!!!!!!!!!!!!!!*/
    public void goToWeight (View view){
        Intent intent = new Intent(this, WeightActivity.class);
        startActivity(intent);
        //EditText editT = (EditText) findViewById(R.id.editT);
    }

    public void goToBlood(View view){
        Intent intent = new Intent(this, BloodPressureActivity.class);
        startActivity(intent);
    }

    public void loginMethod(View view){
        if(getEditText(usernameTextField).equals("Username")) {
            if (getEditText(passwordTextField).equals("Password")) {
                //TODO: Implement page change activity.
            }
        }
        return;
        //Sal Added this

    }

    public String getEditText(EditText text){
        return text.getText().toString();
    }

    public void initializeComponents(){
        this.usernameTextField = findViewById(R.id.usernameTextField);
        this.passwordTextField = findViewById(R.id.passwordTextField);
        this.loginButton = (Button)findViewById(R.id.loginButton);
    }

    /****************
        GETTERS
          AND
        SETTERS
     **************/
    public EditText getUsernameTextField() {
        return usernameTextField;
    }
    public void setUsernameTextField(EditText usernameTextField) {
        this.usernameTextField = usernameTextField;
    }

    public EditText getPasswordTextField() {
        return passwordTextField;
    }
    public void setPasswordTextField(EditText passwordTextField) {
        this.passwordTextField = passwordTextField;
    }

    public Button getLoginButton() {
        return loginButton;
    }
    public void setLoginButton(Button loginButton) {
        this.loginButton = loginButton;
    }
}
