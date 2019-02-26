package com.example.osilifeconnect_ase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.osilifeconnect_ase.DataModels.BloodPressureDataItem;
import com.example.osilifeconnect_ase.DataModels.WeightScaleDataItem;
import com.example.osilifeconnect_ase.Gateways.BloodPressureGateway;
import com.example.osilifeconnect_ase.Gateways.WeightScaleGateway;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText usernameTextField;
    private EditText passwordTextField;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();

        /** Matt's Test**/
        readBloodPressureByMRN("all");//all - grabs all items from csv
        readWeightScaleDataByMRN("all");
        /** **/
    }

    /**
     * Receives the MRN of the patient and returns a list of blood pressure readings.
     * @param MRN The MRN of the patient
     * @return A list of blood pressure readings for the patient
     */
    public List<BloodPressureDataItem> readBloodPressureByMRN(String MRN) {
        System.out.println("Starting to print blood pressure items");
        InputStream inputStream = getResources().openRawResource(R.raw.osi_blood_pressure_test_input);
        BloodPressureGateway bpg = new BloodPressureGateway(inputStream);
        List<BloodPressureDataItem> items = new ArrayList<>();
        /** TESTING PURPOSES **/
        if(MRN.equalsIgnoreCase("all")){
            items = bpg.getCompleteList();
        }else {
            items = bpg.getListByMRN(MRN);
        }
        for(int i = 0; i < items.size(); i++){
            System.out.println(items.get(i).toString());
        }
        System.out.println("Total number of items: " + items.size());
        return items;
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
        List<WeightScaleDataItem> items = new ArrayList<>();
        /** TESTING PURPOSES **/
        if(MRN.equalsIgnoreCase("all")){
            items = wsg.getCompleteList();
        }else{
            items = wsg.getListByMRN(MRN);
        }
        for(int i = 0; i < items.size(); i++){
            System.out.println(items.get(i).toString());
        }
        System.out.println("Total number of items: " + items.size());
        return items;
    }

    /**Cullen messing with stuff!!!!!!!!!!!!!!!!*/
    public void goToWeight (View view){
        Intent intent = new Intent(this, WeightActivity.class);
        startActivity(intent);
        //EditText editT = (EditText) findViewById(R.id.editT);
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
