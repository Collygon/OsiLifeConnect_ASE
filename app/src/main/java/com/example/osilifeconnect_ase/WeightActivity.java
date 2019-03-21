package com.example.osilifeconnect_ase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.osilifeconnect_ase.DataModels.dummyDataWeight;

import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.osilifeconnect_ase.DataModels.dummyDataWeight;

import java.util.Date;


public class WeightActivity extends AppCompatActivity {
    private RecyclerView recView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    //create a bunch of dummyWeight objects
    dummyDataWeight weightObj1 = new dummyDataWeight(0001);
    dummyDataWeight weightObj2 = new dummyDataWeight(0002);
    dummyDataWeight weightObj3 = new dummyDataWeight(0003);
    dummyDataWeight weightObj4 = new dummyDataWeight(0004);
    dummyDataWeight weightObj5 = new dummyDataWeight(0005);
    dummyDataWeight weightObj6 = new dummyDataWeight(0006);

    //array lists to hold the dummy data
    private List<dummyDataWeight> dayList = new ArrayList<>();
    private List<dummyDataWeight> weekList = new ArrayList<>();
    private List<dummyDataWeight> monthList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        //set dummy weights
        weightObj1.setWeightLbs(90.0);
        weightObj2.setWeightLbs(10.0);
        weightObj3.setWeightLbs(80.5);
        weightObj4.setWeightLbs(122.32);
        weightObj5.setWeightLbs(200.12);
        weightObj6.setWeightLbs(100.0);

        //add dummy vals to lists
        dayList.add(weightObj1);
        dayList.add(weightObj2);
        dayList.add(weightObj3);
        dayList.add(weightObj4);
        dayList.add(weightObj5);
        dayList.add(weightObj6);

        weekList.add(weightObj6);
        weekList.add(weightObj3);
        weekList.add(weightObj2);

        monthList.add(weightObj3);

        recView = (RecyclerView)findViewById(R.id.my_recycler_view);
        recView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recView.setLayoutManager(layoutManager);

        adapter = new WeightAdapter(dayList);
        recView.setAdapter(adapter);
    }

    public void getDayData(View view){
        //TODO display day data
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
}
