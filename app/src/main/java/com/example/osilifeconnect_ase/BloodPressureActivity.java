package com.example.osilifeconnect_ase;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.osilifeconnect_ase.DataModels.BloodPressureDataItem;
import com.example.osilifeconnect_ase.DataModels.User;
import com.example.osilifeconnect_ase.DataModels.dummyBloodPressureData;
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
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The Blood Pressure Activity retrieves blood pressure data from
 * the database and displays it to the user. It must feed a list of
 * data to the Blood Pressure Adapter where it will be formatted
 * for display in the list view.
 */
public class BloodPressureActivity extends AppCompatActivity {

    private RecyclerView list;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;
    private User user = User.getUser();

    /*
    dummyBloodPressureData day1 = new dummyBloodPressureData("1111");
    dummyBloodPressureData day2 = new dummyBloodPressureData("1112");
    dummyBloodPressureData day3 = new dummyBloodPressureData("1113");
    dummyBloodPressureData day4 = new dummyBloodPressureData("1114");
    dummyBloodPressureData day5 = new dummyBloodPressureData("1115");
    dummyBloodPressureData day6 = new dummyBloodPressureData("1116");
    */
    private List<dummyBloodPressureData> tempAllData=new ArrayList<>();
    private List<dummyBloodPressureData> tempDayData = new ArrayList<>();
    private List<dummyBloodPressureData> dayData = new ArrayList<>();
    private List<dummyBloodPressureData> weekData = new ArrayList<>();
    private List<dummyBloodPressureData> monthData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure);
        new BloodPressureSendTask().execute();
        try {
            wait(10000);
        }catch (Exception e){
            System.out.println("Exception in BP Activity Main");
        }
        new BloodPressureTask().execute();


        list = (RecyclerView)findViewById(R.id.my_recycler_view);
        list.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

        mAdapter = new BloodPressureAdapter(dayData);
        list.setAdapter(mAdapter);

    }

    void setWeeks(){
       //dummyBloodPressureData obj= new dummyBloodPressureData(user.getMrn());
       double avgSys=0;
       double avgDyno=0;
       int avgPulse=0;
       int dayCount=0;
        String beginDate="";
        String endDate="";
        String weekDate="";
       int x=1;
        for(int i=0;i<tempAllData.size();i++){
            if (dayCount !=7){
                if (dayCount==0)
                    beginDate=tempAllData.get(i).getDateToString();
                if(dayCount==6)
                    endDate=tempAllData.get(i).getDateToString();
                avgDyno+=tempAllData.get(i).getDynostolic();
                System.out.println("Dy: "+tempAllData.get(i).getDynostolic());
                avgSys+=tempAllData.get(i).getSystolic();
                System.out.println("Sys: "+tempAllData.get(i).getSystolic());
                avgPulse+=tempAllData.get(i).getPulseRate();
                System.out.println("Pulse: "+tempAllData.get(i).getPulseRate());
                dayCount++;
                System.out.println("daycount: "+ dayCount);
                       }
            if (dayCount==7){
                Date date=new Date();
                dummyBloodPressureData obj= new dummyBloodPressureData(user.getMrn());
                avgDyno=avgDyno/7;
                avgPulse=avgPulse/7;
                avgSys=avgSys/7;
                System.out.println("This is week "+x+":"+ avgDyno+" "+avgSys+" "+ avgPulse);
                x++;
                obj.setPulseRate(avgPulse);
                obj.setDynostolic(avgDyno);
                obj.setSystolic(avgSys);
                obj.setDate(date);
                try {
                    weekDate=("Week of " + beginDate + " to " + endDate);
                }catch (Exception e){
                    System.out.println("Date not initialized");
                }
                System.out.println("The date of week "+ x+ " is " + weekDate);
                obj.setDateString(weekDate);
                weekData.add(obj);

                dayCount=0;
                avgDyno=0;
                avgPulse=0;
                avgSys=0;
                weekDate="";
            }
        }
    }

    void setLists(){
        setWeeks();
        String dayDate="";
        String monthDate="";

        int sz=tempAllData.size()-1;
        for(int i=0;i<7;i++){
            dayDate=tempAllData.get(sz).getDate().toString();
            tempAllData.get(sz).setDateString(dayDate);
            dayData.add(tempAllData.get(sz));
            sz--;
        }


        double avgSys=0;
        double avgDyno=0;
        int avgPulse=0;
        Date date=dayData.get(0).getDate();
        dummyBloodPressureData obj=new dummyBloodPressureData(user.getMrn());
        dummyBloodPressureData monthObj=new dummyBloodPressureData(user.getMrn());
        boolean isFound=false;
        for(int i=0;i<tempAllData.size();i++){
            isFound=tempAllData.get(i).getDateToString().contains("04-");
            if(isFound==true){
                System.out.println("All Data "+i+":"+ tempAllData.get(i).getDateToString());

                avgDyno+=tempAllData.get(i).getDynostolic();
                avgPulse+=tempAllData.get(i).getPulseRate();
                avgSys+=tempAllData.get(i).getSystolic();
            }

        }


        avgDyno=avgDyno/tempAllData.size();
        avgSys=avgSys/tempAllData.size();
        avgPulse=avgPulse/tempAllData.size();

        monthObj.setPulseRate(avgPulse);
        monthObj.setDynostolic(avgDyno);
        monthObj.setSystolic(avgSys);
        monthObj.setDate(date);
        monthDate="Month of April";
        monthObj.setDateString(monthDate);
        monthData.add(monthObj);


    }

    public void sendBP(){
        System.out.println("Sent BP");
    }

    public void getDayData(View view){
        //TODO display day data
        /*list = (RecyclerView) findViewById(R.id.my_recycler_view);
        list.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);*/

        mAdapter = new BloodPressureAdapter(dayData);
        list.setAdapter(mAdapter);

    }

    public void getWeekData(View view){
        //TODO display week data
        /*list = (RecyclerView) findViewById(R.id.my_recycler_view);
        list.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);*/

        mAdapter = new BloodPressureAdapter(weekData);
        list.setAdapter(mAdapter);

    }

    public void getMonthData(View view){
        //TODO display month data
        /*list = (RecyclerView) findViewById(R.id.my_recycler_view);
        list.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);*/

        mAdapter = new BloodPressureAdapter(monthData);
        list.setAdapter(mAdapter);

    }

    class BloodPressureTask extends AsyncTask<Void,Void,String> {
        // private final String TAG = WeightActivity.getWeight.class.getSimpleName();
        @Override
        protected void onPreExecute() {
            Toast.makeText(BloodPressureActivity.this,"Attempting to retrieve blood pressure data",Toast.LENGTH_LONG).show();
            System.out.print("Attepting to retrieve\n");
        }

        protected String doInBackground(Void... params) {
            String mrn = User.getUser().getMrn(), returnMessage = "Failed to get Blood pressure data";
            //System.out.println("username: " + username + " paassword: " + password);
            // Making a request to url and getting response
            String reqUrl = "http://ec2-13-58-1-146.us-east-2.compute.amazonaws.com/bpImport/readBPImport.php";

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
                System.out.println("Response in Blood Pressure"+ response);

            }
            catch (Exception e) {
                System.out.print("BP exception");
            }

            if (response != null) {
                try {
                    System.out.println("Here is the response in Blood Pressure: " + response);

                    JSONObject jsonObj = new JSONObject(response);
                    if(jsonObj.getInt("success") == 1) {
                        // Getting JSON Array node
                        JSONArray bloodPressures = jsonObj.getJSONArray("blood_pressure_import");
                        dummyBloodPressureData obj;
                        // looping through All Contacts
                        for (int i = 0; i < bloodPressures.length(); i++) {
                            JSONObject p = bloodPressures.getJSONObject(i);
                            obj=new dummyBloodPressureData(mrn);
                            Double systolic =p.getDouble("systolic");
                            obj.setSystolic(systolic);
                            Double dystolic=p.getDouble("dystolic");
                            obj.setDynostolic(dystolic);
                            int pulseRate=p.getInt("pulse");
                            obj.setPulseRate(pulseRate);

                            String d1 = p.getString("reading_date_time");

                            Date date34;
                            try {
                                SimpleDateFormat ts = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                date34 = ts.parse(d1);
                                obj.setDate(date34);
                            }catch(ParseException e){
                                e.printStackTrace();
                            }


                            tempAllData.add(obj);


                        }
                        returnMessage = "blood pressure retrieval Success";
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

        protected void onPostExecute(String result) {
            Toast.makeText(BloodPressureActivity.this, result, Toast.LENGTH_LONG).show();
            if(result.equals("blood pressure retrieval Success")) {
                setLists();

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

    class BloodPressureSendTask extends AsyncTask<Void,Void,String> {
        // private final String TAG = WeightActivity.getWeight.class.getSimpleName();
        @Override
        protected void onPreExecute() {
            Toast.makeText(BloodPressureActivity.this,"Attempting to retrieve blood pressure data",Toast.LENGTH_LONG).show();
            System.out.print("Attepting to retrieve\n");
        }

        protected String doInBackground(Void... params) {
            String mrn = User.getUser().getMrn(), returnMessage = "Failed to get Blood pressure data";
            //System.out.println("username: " + username + " paassword: " + password);
            // Making a request to url and getting response
            String reqUrl = "http://ec2-13-58-1-146.us-east-2.compute.amazonaws.com/bpImport/createBPImport.php";

            BloodPressureDataItem item = new BloodPressureDataItem(user.getMrn(),"username","device1","manufacturer1","999","999","2019-12-12 02:05:06",10.0,110.0,90.0);
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
                        + "&" + URLEncoder.encode("dystolic", charSet) + "=" +URLEncoder.encode(item.getDystolic()+"", charSet)+ "&"+
                        URLEncoder.encode("systolic", charSet) + "=" +URLEncoder.encode(item.getSystolic()+"", charSet)+ "&"+
                        URLEncoder.encode("pulse", charSet) + "=" +URLEncoder.encode(item.getPulse()+"", charSet);
                bw.write(data);
                bw.flush();
                bw.close();
                OS.close();
                // read the response
                InputStream in = new BufferedInputStream(conn.getInputStream());
                response = convertStreamToString(in);
                System.out.println("Response in Blood Pressure"+ response);

            }
            catch (Exception e) {
                System.out.print("BP exception");
            }

            if (response != null) {
                try {
                    System.out.println("Here is the response in Blood Pressure: " + response);

                    JSONObject jsonObj = new JSONObject(response);
                    if(jsonObj.getInt("success") == 1) {

                        returnMessage = "blood pressure retrieval Success";
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

        protected void onPostExecute(String result) {
            Toast.makeText(BloodPressureActivity.this, result, Toast.LENGTH_LONG).show();
            if(result.equals("blood pressure retrieval Success")) {
               // setLists();
                sendBP();
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
