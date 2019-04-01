package com.example.osilifeconnect_ase;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.osilifeconnect_ase.DataModels.BloodPressureDataItem;
import com.example.osilifeconnect_ase.DataModels.WeightScaleDataItem;
import com.example.osilifeconnect_ase.Gateways.BloodPressureGateway;
import com.example.osilifeconnect_ase.Gateways.WeightScaleGateway;

import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText usernameTextField;
    private EditText passwordTextField;
    private TextView alertText;
    private Button loginButton;
    private int loginAttempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MAIN", "Starting App...");
        super.onCreate(savedInstanceState);
        Log.d("MAIN", "Setting content view.");
        setContentView(R.layout.activity_main);
        Log.d("MAIN", "Content set. Initializing.");
        alertText = findViewById(R.id.loginAlertText);
        alertText.setVisibility(View.GONE);
        initializeComponents();
        //Login Listener START
        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                loginMethod(v);
            }
        });
        //Login Listener END
    }

    public void loginMethod(View view){
        if(testCredentials()){
                Intent dashIntent = new Intent(this, dashboardActivity.class);
                Log.d("DASH INTENT", "Intent Generated");
                startActivity(dashIntent);
        }
        else if(loginAttempts >= 1){
            anmLoginAttempt();
            loginAttempts++;
        }
        else {
            alertText.setVisibility(View.VISIBLE);
            loginAttempts++;
        }
    }

    public String getEditText(EditText text){
        return text.getText().toString();
    }

    public boolean testCredentials(){
        return getEditText(usernameTextField).equals("Username") && getEditText(passwordTextField).equals("Password");
    }

    public void initializeComponents(){
        this.usernameTextField = findViewById(R.id.usernameTextField);
        this.passwordTextField = findViewById(R.id.passwordTextField);
        this.loginButton = findViewById(R.id.loginButton);
    }

    public void anmLoginAttempt() {
        ObjectAnimator bounce = ObjectAnimator.ofFloat(alertText, "translationY", 0, -25, 0);
        bounce.setStartDelay(10);
        bounce.setDuration(300);
        bounce.start();
    }

    /**
     * Receives the MRN of the patient and returns a list of blood pressure readings.
     * @param MRN The MRN of the patient
     * @return A list of blood pressure readings for the patient
     */
    public List<BloodPressureDataItem> readBloodPressureByMRN(String MRN) {
        //System.out.println("Starting to print blood pressure items");
        InputStream inputStream = getResources().openRawResource(R.raw.osi_blood_pressure_test_input);
        BloodPressureGateway bpg = new BloodPressureGateway(inputStream);
        /**     To Test Output !!!
        List<BloodPressureDataItem> items = bpg.getListByMRN(MRN);
        //List<BloodPressureDataItem> items = bpg.getCompleteList();
        for(int i = 0; i < items.size(); i++){
            System.out.println(items.get(i).toString());
        }
        System.out.println("Total number of items: " + items.size());

        return items;
        // **/
        return bpg.getListByMRN(MRN);
    }

    /**
     * Receives the MRN of the patient and returns a list of weight readings.
     * @param MRN The MRN of the patient
     * @return A list of weight readings for the patient
     */
    public List<WeightScaleDataItem> readWeightScaleDataByMRN(String MRN) {
        System.out.println("Starting to print weight scale items");
        InputStream inputStream = getResources().openRawResource(R.raw.osi_weight_scale_test_input);
        WeightScaleGateway wsg = new WeightScaleGateway(inputStream);
        /**     To Test Output !!!
        List<WeightScaleDataItem> items = wsg.getListByMRN(MRN);
        //List<WeightScaleDataItem> items  = wsg.getCompleteList();
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i).toString());
        }
        System.out.println("Total number of items: " + items.size());
        return items;
         //**/
        return wsg.getListByMRN(MRN);
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
