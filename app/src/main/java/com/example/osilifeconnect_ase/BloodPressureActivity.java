package com.example.osilifeconnect_ase;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BloodPressureActivity extends AppCompatActivity {

    private RecyclerView list;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;
    private String[] dayData = {"DayData", "Much", "Data", "Very", "Blood", "Pressure", "WOW"};
    private String[] weekData = {"WeekData", "Much", "Data", "Very", "Blood", "Pressure", "WOW"};
    private String[] monthData = {"MonthData", "Much", "Data", "Very", "Blood", "Pressure", "WOW"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure);

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
