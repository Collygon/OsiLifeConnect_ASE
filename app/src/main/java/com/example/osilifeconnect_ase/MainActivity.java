package com.example.osilifeconnect_ase;

import android.animation.ObjectAnimator;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class MainActivity extends AppCompatActivity {

    //TextField for the UserName
    private EditText usernameTextField;

    //TextField for the password
    private EditText passwordTextField;

    //Text for alerting the user for failed login attempts
    private TextView alertText;

    //Button the user presses to attempt a login
    private Button loginButton;

    //Variable for keeping track of the users login attempts. Used for alert animations.
    private int loginAttempts = 0;

    //Checkbox to allow the user to save their login info or not
    private CheckBox userCheckBox;

    // Android Studio variable that holds the users login information between app uses
    private SharedPreferences loginPrefs;

    // String to be used by the SharedPreference for storing the user login info
    private static final String PREFS_NAME = "PrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alertText = findViewById(R.id.loginAlertText);
        alertText.setVisibility(View.GONE);

        loginPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Function that sets this activity's view items
        initializeComponents();

        // Retrieves and Sets the users login info using SharedPreferences
        retrievePrefs();

        /*******************
         * Listener for the login button.
         * Waits for the user to select the button, then calls the login function.
         */
        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                loginMethod(v);
            }
        });
        //Login Listener END
    }

    /******************************
     * Runs at this activity's start.
     * Checks the stored SharedPreferences to see if the user's login information is stored.
     * If so, sets the text to hold the corresponding into.
     * !! There is no encryption currently implemented on these stored strings !!
     */
    private void retrievePrefs() {
        SharedPreferences retPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if(retPrefs.contains("pref_user_name")){
            String prefUserName = retPrefs.getString("pref_user_name", "not_available");
            usernameTextField.setText(prefUserName);
        }
        if(retPrefs.contains("pref_user_pass")){
            String prefUserPass = retPrefs.getString("pref_user_pass", "not_available");
            passwordTextField.setText(prefUserPass);
        }
        if(retPrefs.contains("pref_log_check")){
            boolean prefCheck = retPrefs.getBoolean("pref_log_check", false);
            userCheckBox.setChecked(prefCheck);
        }

    }

    /***************************
     * Runs when the login button is selected by the user.
     * Calls other functions to determine whether the app should progress or not.
     * @param view
     */
    public void loginMethod(View view){
        String username = usernameTextField.getText().toString();
        String password = passwordTextField.getText().toString();
        checkboxAnalysis();
        Intent dashIntent = new Intent(this, dashboardActivity.class);
        Log.d("DASH INTENT", "Intent Generated");
        notificationPackage.getInstance().loginNotify(this);
        startActivity(dashIntent);
        //execute loginTask
        //new LoginTask().execute(username, password);
    }

    /*******************************************
     * Takes in a boolean parameter that is true when the users credentials are correct.
     * If true, runs checkboxAnalysis then opens the next activity.
     * If false, increments the login attempts variable.
     * If done so for the first time, displays the text alerting the user that their information is wrong.
     * @param success
     */
    public void LogIn(boolean success){
        if(success) {
            checkboxAnalysis();
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

    /***************************************
     * Used to set the users SharedPreferences. This function only runs when the user successfully logs in.
     * If the checkbox is checked, then the users information is saved in the SharedPreferences.
     * If not, then the SharedPreferences are cleared entirely.
     */
    public void checkboxAnalysis(){
        if(userCheckBox.isChecked()){
            boolean remCheck = userCheckBox.isChecked();
            SharedPreferences.Editor prefEdit = loginPrefs.edit();
            prefEdit.putString("pref_user_name", getEditText(usernameTextField));
            prefEdit.putString("pref_user_pass", getEditText(passwordTextField));
            prefEdit.putBoolean("pref_log_check", remCheck);
            prefEdit.apply();
            Toast.makeText(this, "User credentials saved.", Toast.LENGTH_SHORT).show();
        }else{
            loginPrefs.edit().clear().apply();
        }
    }

    /************************
     * Simply function to return, in string format, the text in an edit text box.
     * @param text
     * @return String
     */
    public String getEditText(EditText text){
        return text.getText().toString();
    }

    /******************************************************
     * Runs upon creation of the activity
     * Sets the xml items into local variables for function use.
     */
    public void initializeComponents(){
        this.usernameTextField = findViewById(R.id.usernameTextField);
        this.passwordTextField = findViewById(R.id.passwordTextField);
        this.loginButton = findViewById(R.id.loginButton);
        this.userCheckBox = findViewById(R.id.userCheckBox);
        createNotificationChannel();
    }

    /**************************
     * Animates the loginAlert text when the user surpasses more than 1 failed login attempt.
     */
    public void anmLoginAttempt() {
        ObjectAnimator bounce = ObjectAnimator.ofFloat(alertText, "translationY", 0, -25, 0);
        bounce.setStartDelay(10);
        bounce.setDuration(300);
        bounce.start();
    }

    /*************************
     * This function must run when the app opens in order for the notifications to work.
     * Sets a name for the notifications sequence and description.
     * Accesses the singleton class 'notificationPackage' to establish notifications for global calls.
     */
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
                    System.out.println("Here is the response: " + response);
                    JSONObject jsonObj = new JSONObject(response);
                    if(jsonObj.getInt("success") == 1) {
                        // Getting JSON Array node
                        JSONArray patients = jsonObj.getJSONArray("patient");

                        // looping through All Contacts
                        for (int i = 0; i < patients.length(); i++) {
                            JSONObject p = patients.getJSONObject(i);
                            String mrn = p.getString("mrn");
                           // System.out.print("MRN:"+mrn + " in main\n");
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
