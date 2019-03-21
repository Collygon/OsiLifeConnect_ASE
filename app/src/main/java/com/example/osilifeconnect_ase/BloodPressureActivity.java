package com.example.osilifeconnect_ase;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.osilifeconnect_ase.DataModels.dummyBloodPressureData;

import java.util.ArrayList;
import java.util.List;

public class BloodPressureActivity extends AppCompatActivity {

    private RecyclerView list;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;
    dummyBloodPressureData day1 = new dummyBloodPressureData(1111);
    dummyBloodPressureData day2 = new dummyBloodPressureData(1112);
    dummyBloodPressureData day3 = new dummyBloodPressureData(1113);
    dummyBloodPressureData day4 = new dummyBloodPressureData(1114);
    dummyBloodPressureData day5 = new dummyBloodPressureData(1115);
    dummyBloodPressureData day6 = new dummyBloodPressureData(1116);
    private List<dummyBloodPressureData> dayData = new ArrayList<dummyBloodPressureData>();
    private List<dummyBloodPressureData> weekData = new ArrayList<dummyBloodPressureData>();
    private List<dummyBloodPressureData> monthData = new ArrayList<dummyBloodPressureData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure);

        day1.setSystolic(100);
        day1.setDynostolic(62);
        day1.setPulseRate(69);
        day2.setSystolic(96);
        day2.setDynostolic(57);
        day2.setPulseRate(79);
        day3.setSystolic(119);
        day3.setDynostolic(66);
        day3.setPulseRate(69);
        day4.setSystolic(98);
        day4.setDynostolic(60);
        day4.setPulseRate(75);
        day5.setSystolic(101);
        day5.setDynostolic(65);
        day5.setPulseRate(70);
        day6.setSystolic(97);
        day6.setDynostolic(57);
        day6.setPulseRate(80);
        dayData.add(day6);
        weekData.add(day4);
        weekData.add(day5);
        weekData.add(day6);
        monthData.add(day1);
        monthData.add(day2);
        monthData.add(day3);
        monthData.add(day4);
        monthData.add(day5);
        monthData.add(day6);


        list = (RecyclerView)findViewById(R.id.my_recycler_view);
        list.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

        mAdapter = new BloodPressureAdapter(dayData);
        list.setAdapter(mAdapter);

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
}
