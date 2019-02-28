package com.example.osilifeconnect_ase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BloodPressureActivity extends AppCompatActivity {

    private RecyclerView list;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure);

        String[] dayData = {"DayData", "Much", "Data", "Very", "Blood", "Pressure", "WOW"};
        list = findViewById(R.id.recyclerView);
        list.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

        mAdapter = new BloodPressureAdapter(dayData);
        list.setAdapter(mAdapter);

        Intent intent = getIntent();
    }

    public void getDayData(View view){
        //TODO display day data
        String[] dayData = {"DayData", "Much", "Data", "Very", "Blood", "Pressure", "WOW"};
        list = (RecyclerView) findViewById(R.id.recyclerView);
        list.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

        mAdapter = new BloodPressureAdapter(dayData);
        list.setAdapter(mAdapter);

    }

    public void getWeekData(View view){
        //TODO display week data
        String[] weekData = {"WeekData", "Much", "Data", "Very", "Blood", "Pressure", "WOW"};
        list = (RecyclerView) findViewById(R.id.recyclerView);
        list.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

        mAdapter = new BloodPressureAdapter(weekData);
        list.setAdapter(mAdapter);

    }

    public void getMonthData(View view){
        //TODO display month data
        String[] monthData = {"MonthData", "Much", "Data", "Very", "Blood", "Pressure", "WOW"};
        list = (RecyclerView) findViewById(R.id.recyclerView);
        list.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

        mAdapter = new BloodPressureAdapter(monthData);
        list.setAdapter(mAdapter);

    }
}
