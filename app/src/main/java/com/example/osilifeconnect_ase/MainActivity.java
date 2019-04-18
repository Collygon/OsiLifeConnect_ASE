package com.example.osilifeconnect_ase;

import android.animation.ObjectAnimator;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.osilifeconnect_ase.DataModels.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
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
        String username = usernameTextField.getText().toString();
        String password = passwordTextField.getText().toString();
        new LoginTask().execute(username, password);
    }

    public void LogIn(boolean success){
        if(success) {
            Intent dashIntent = new Intent(this, dashboardActivity.class);
            Log.d("DASH INTENT", "Intent Generated");
            notificationPackage.getInstance().loginNotify(this);
            startActivity(dashIntent);
        }else if(loginAttempts >= 1){
            anmLoginAttempt();
            loginAttempts++;
        }
        else {
            alertText.setVisibility(View.VISIBLE);
            loginAttempts++;
        }
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
        createNotificationChannel();
    }

    public void anmLoginAttempt() {
        ObjectAnimator bounce = ObjectAnimator.ofFloat(alertText, "translationY", 0, -25, 0);
        bounce.setStartDelay(10);
        bounce.setDuration(300);
        bounce.start();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "notifySequence";
            String description = "notifyDescription";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(notificationPackage.getInstance().getChannelId(), name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
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

    /**
     * Creates an asynchronized task to hand the database access for the user's login
     */
    class LoginTask extends AsyncTask<String, Void, String> {
        private final String TAG = LoginTask.class.getSimpleName();
        @Override
        protected void onPreExecute() {
            Toast.makeText(MainActivity.this,"Attempting to Login",Toast.LENGTH_LONG).show();

        }

        @Override
        protected String doInBackground(String... params) {
            String username = params[0], password = params[1], returnMessage = "Login Failed";
            System.out.println("username: " + username + " paassword: " + password);
            // Making a request to url and getting response
            String reqUrl = "http://ec2-13-58-1-146.us-east-2.compute.amazonaws.com/patient/readPatient.php";

            String charSet = "UTF-8";
            String response = null;
            try {
                URL url = new URL(reqUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                OutputStream OS = conn.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(OS));
                String data = URLEncoder.encode("login_id", charSet) + "=" +URLEncoder.encode(username, charSet) + "&" +
                        URLEncoder.encode("login_pw", charSet) + "=" + URLEncoder.encode("password", charSet);
                bw.write(data);
                bw.flush();
                bw.close();
                OS.close();
                // read the response
                InputStream in = new BufferedInputStream(conn.getInputStream());
                response = convertStreamToString(in);
                System.out.println("Response: " + response);
            } catch (MalformedURLException e) {
                Log.e(TAG, "MalformedURLException: " + e.getMessage());
            } catch (ProtocolException e) {
                Log.e(TAG, "ProtocolException: " + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "IOException: " + e.getMessage());
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }

            if (response != null) {
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    if(jsonObj.getInt("success") == 1) {
                        // Getting JSON Array node
                        JSONArray patients = jsonObj.getJSONArray("patient");

                        // looping through All Contacts
                        for (int i = 0; i < patients.length(); i++) {
                            JSONObject p = patients.getJSONObject(i);
                            String mrn = p.getString("mrn");
                            String loginID = p.getString("login_id");
                            String loginPW = p.getString("login_pw");

                            // adding contact to contact list
                            User.getUser().setMrn(mrn);
                            User.getUser().setLoginID(loginID);
                            User.getUser().setLoginPW(loginPW);
                         //   System.out.println("User: " + User.getUser().toString());
                        }
                        returnMessage = "Login Success";
                    }else{
                        System.out.println("Failed to get patient" + jsonObj.toString());
                    }
                } catch (final JSONException e) {
                    //Log.e(TAG, "Json parsing error: " + e.getMessage());
                    e.printStackTrace();


                }

            } else {
                //Log.e(TAG, "Couldn't get json from server.");
                System.out.println("Connection Failed");
            }

            return returnMessage;
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
            if(result.equalsIgnoreCase("login success")){
                LogIn(true);
            }else{
                LogIn(false);
            }
        }

        private String convertStreamToString(InputStream is) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return sb.toString();
        }
    }

}
