package com.example.osilifeconnect_ase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class WeightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        Intent intent = getIntent();
    }

    public void getDayData(View view){
        //TODO display day data
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("2/26/19 11:22am");

        textView = (TextView) findViewById(R.id.textView2);
        textView.setText("365lbs");
    }

    public void getWeekData(View view) {
        //TODO display week data
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("2/26/19 11:22am");

        textView = (TextView) findViewById(R.id.textView2);
        textView.setText("365lbs");
    }
    public void getMonthData(View view){
        //TODO display month data
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("2/26/19 11:22am");

        textView = (TextView) findViewById(R.id.textView2);
        textView.setText("365lbs");
    }
}
