package com.example.osilifeconnect_ase;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class dashboardActivity extends AppCompatActivity {

    private DrawerLayout cDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DASHBOARD", "Dashboard successfully opened.");
        super.onCreate(savedInstanceState);
        Log.d("DASHBOARD", "Instance State completed.");
        setContentView(R.layout.activity_dashboard);
        Log.d("DASHBOARD", "End of dashboard creation.");
        cDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        menuItem.setChecked(true);
                        cDrawerLayout.closeDrawers();


                        return true;
                    }
                }
        );
    }

}
