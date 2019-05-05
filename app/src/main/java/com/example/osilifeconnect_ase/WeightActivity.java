package com.example.osilifeconnect_ase;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.osilifeconnect_ase.DataModels.User;
import com.example.osilifeconnect_ase.DataModels.WeightScaleDataItem;
import com.example.osilifeconnect_ase.DataModels.dummyDataWeight;

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
//import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Locale;

/**
 * The Weight Activity retrieves weight data from
 * the database and displays it to the user. It must feed a list of
 * data to the Weight Adapter where it will be formatted
 * for display in the list view.
 */
public class WeightActivity extends AppCompatActivity {
    private RecyclerView recView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private User user = User.getUser();



    //array lists to hold the dummy data

    private List<dummyDataWeight> temp=new ArrayList<>();
    private List<dummyDataWeight> dayList = new ArrayList<>();
    private List<dummyDataWeight> weekList = new ArrayList<>();
    private List<dummyDataWeight> monthList = new ArrayList<>();
    private WeightActivity bluetoothService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        new WeightTaskSend().execute();

        //set dummy weights
        /*
        weightObj1.setWeightLbs(90.0);
        weightObj2.setWeightLbs(10.0);
        weightObj3.setWeightLbs(80.5);
        weightObj4.setWeightLbs(122.32);
        weightObj5.setWeightLbs(200.12);
        weightObj6.setWeightLbs(100.0);
        */
        //add dummy vals to lists
      /*  for(int i=0;i<weightArr.length;i++){
            dayList.add(weightArr[i]);
        }*/
        /*
        dayList.add(weightObj1);
        dayList.add(weightObj2);
        dayList.add(weightObj3);
        dayList.add(weightObj4);
        dayList.add(weightObj5);
        dayList.add(weightObj6);
        */
        //weekList.add(temp.get(0));
        //weekList.add(temp.get(1));
        //weekList.add(temp.get(2));

       // monthList.add(weightObj1);

       // dummyDataWeight obj=new dummyDataWeight("0000");
        //obj.setWeightLbs(250);
        //obj.setWeightKgs(250);
        //dayList=temp;

        recView = findViewById(R.id.my_recycler_view);
        recView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recView.setLayoutManager(layoutManager);

        adapter = new WeightAdapter(dayList);
        recView.setAdapter(adapter);

    }

    void setDayList(){
        System.out.print("Weight send function");
        dayList=temp;
        //for(int i=0;i <dayList.size();i++) {
          //  System.out.println("daylist" + i +": " + dayList.get(i).getWeightLbs());
       // }


    }

    public void getDayData(View view){
        //TODO display day data
        //new WeightTask();
        adapter = new WeightAdapter(dayList);
        recView.setAdapter(adapter);
    }

    public void getWeekData(View view) {
        //TODO display week data

        adapter = new WeightAdapter(weekList);
        recView.setAdapter(adapter);
    }
    public void getMonthData(View view){
        //TODO display month data
        adapter = new WeightAdapter(monthList);
        recView.setAdapter(adapter);
    }

    /*
    class WeightTask extends AsyncTask<Void,Void,String>{
       // private final String TAG = WeightActivity.getWeight.class.getSimpleName();
        @Override
        protected void onPreExecute() {
            Toast.makeText(WeightActivity.this,"Attempting to retrieve weight data",Toast.LENGTH_LONG).show();
            System.out.print("Attepting to retrieve\n");
        }

        protected String doInBackground(Void... params) {
            String mrn = User.getUser().getMrn(), returnMessage = "Failed to get weight data";
            //System.out.println("username: " + username + " paassword: " + password);
            // Making a request to url and getting response
            String reqUrl = "http://ec2-13-58-1-146.us-east-2.compute.amazonaws.com/wImport/readWImport.php";
            String charSet = "UTF-8";
            String response = null;
            try {
                URL url = new URL(reqUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                OutputStream OS = conn.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(OS));
                String data = URLEncoder.encode("mrn", charSet) + "=" +URLEncoder.encode(mrn, charSet);
                bw.write(data);
                bw.flush();
                bw.close();
                OS.close();
                // read the response
                InputStream in = new BufferedInputStream(conn.getInputStream());
                response = convertStreamToString(in);
                System.out.println("Response in Weight"+ response);

            }
            catch (Exception e) {
                System.out.print("Weight exception");
            }

            if (response != null) {
                try {
                    System.out.println("Here is the response in weight: " + response);

                    JSONObject jsonObj = new JSONObject(response);
                    if(jsonObj.getInt("success") == 1) {
                        // Getting JSON Array node
                        JSONArray weights = jsonObj.getJSONArray("weight_import");
                            dummyDataWeight obj;
                        // looping through All Contacts
                        for (int i = 0; i < weights.length(); i++) {
                            JSONObject p = weights.getJSONObject(i);
                            obj=new dummyDataWeight(mrn);
                            Double weight2 =p.getDouble("weight");
                            obj.setWeightLbs(weight2);
                            obj.setWeightKgs(weight2);
                            String d1 = p.getString("reading_date_time");

                            Date date34;
                            try {
                                SimpleDateFormat ts = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                date34 = ts.parse(d1);
                                obj.setDate(date34);
                            }catch(ParseException e){
                                e.printStackTrace();
                            }
                            temp.add(obj);
                            //weightObj1.setWeightLbs(weight2);
                            Log.d("JSON","Inside JSON loop");


                            //   System.out.println("User: " + User.getUser().toString());
                        }
                        returnMessage = "weight retrieval Success";
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

            System.out.println("Return Message in weight send: " + returnMessage);

            return returnMessage;
        }

        protected void onPostExecute(String result) {
            Toast.makeText(WeightActivity.this, result, Toast.LENGTH_LONG).show();
           if(result.equals("weight retrieval Success")) {
               setDayList();

               System.out.print("executed\n");
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
    */

    class WeightTaskSend extends AsyncTask<Void,Void,String>{
        // private final String TAG = WeightActivity.getWeight.class.getSimpleName();
        @Override
        protected void onPreExecute() {
            Toast.makeText(WeightActivity.this,"Attempting to retrieve weight data",Toast.LENGTH_LONG).show();
            System.out.print("Attepting to retrieve\n");
        }

        protected String doInBackground(Void... params) {
            String mrn = User.getUser().getMrn(), returnMessage = "Failed to get weight data";
            //System.out.println("username: " + username + " paassword: " + password);
            // Making a request to url and getting response
            String reqUrl = "http://ec2-13-58-1-146.us-east-2.compute.amazonaws.com/wImport/createWImport.php";
           // String reqUrl = "http://ec2-13-58-1-146.us-east-2.compute.amazonaws.com/wCompute/createWCompute.php";

            WeightScaleDataItem item=new WeightScaleDataItem(user.getMrn(),"username","device1","manu1","69","420","2019-12-12 02:05:06",170.0);
            String charSet = "UTF-8";
            String response = null;
            try {
                URL url = new URL(reqUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                OutputStream OS = conn.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(OS));
                String data = URLEncoder.encode("mrn", charSet) + "=" +URLEncoder.encode(mrn, charSet)+ "&" +
                        URLEncoder.encode("login_id", charSet) + "=" +URLEncoder.encode(item.getLoginID(), charSet) + "&" +
                        URLEncoder.encode("device_type", charSet) + "=" +URLEncoder.encode(item.getDeviceType(), charSet) + "&"+
                        URLEncoder.encode("manufacturer", charSet) + "=" +URLEncoder.encode(item.getManufacturer(), charSet) + "&" +
                        URLEncoder.encode("model_number", charSet) + "=" +URLEncoder.encode(item.getModelNumber(), charSet)+ "&" +
                        URLEncoder.encode("serial_number", charSet) + "=" +URLEncoder.encode(item.getSerialNumber(), charSet)+ "&" +
                        URLEncoder.encode("reading_date_time", charSet) + "=" +URLEncoder.encode(item.getReadingDateTime(), charSet)
                        + "&" + URLEncoder.encode("weight", charSet) + "=" +URLEncoder.encode(item.getWeight()+"", charSet);
                bw.write(data);
                bw.flush();
                bw.close();
                OS.close();
                // read the response
                InputStream in = new BufferedInputStream(conn.getInputStream());
                response = convertStreamToString(in);
                System.out.println("Response in Weight"+ response);

            }
            catch (Exception e) {
                System.out.print("Weight exception");
            }

            if (response != null) {
                try {
                    System.out.println("Here is the response in weight: " + response);

                    JSONObject jsonObj = new JSONObject(response);
                    if(jsonObj.getInt("success") == 1) {

                        returnMessage = "weight retrieval Success";
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

            System.out.println("Return Message in weight send: " + returnMessage);

            return returnMessage;
        }

        protected void onPostExecute(String result) {
            Toast.makeText(WeightActivity.this, result, Toast.LENGTH_LONG).show();
            if(result.equals("weight retrieval Success")) {
                setDayList();

                System.out.print("executed\n");
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
