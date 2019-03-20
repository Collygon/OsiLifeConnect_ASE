package com.example.osilifeconnect_ase;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class singletonPackage {

    private static singletonPackage instance = null;

    public static singletonPackage getInstance() {
        if(instance == null)
            instance = new singletonPackage();
        return instance;
    }

    public Intent switchActivity(String title, Context context){
        Intent intent;
        switch(title) {
            case "Weight":
                intent = new Intent(context, WeightActivity.class);
                Log.d("SINGLETON INTENT", "Weight Intent Generated");
                return intent;
            case "Devices":
                intent = new Intent(context, dashboardActivity.class);
                Log.d("SINGLETON INTENT", "Devices Intent Generated");
                return intent;
            case "Contact Us":
                intent = new Intent(context, dashboardActivity.class);
                Log.d("SINGLETON INTENT", "Contact Us Intent Generated");
                return intent;
            case "Settings":
                intent = new Intent(context, SettingsActivity.class);
                Log.d("SINGLETON INTENT", "Settings Intent Generated");
                return intent;
            case "Blood Pressure":
                intent = new Intent(context, BloodPressureActivity.class);
                Log.d("SINGLETON INTENT", "Settings Intent Generated");
                return intent;
        }
        return null;
    }

}
