package com.example.osilifeconnect_ase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toolbar;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        
        
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        menuItem.setChecked(true);
                        Log.d("MENU ITEM", "Selected" + menuItem.getTitle().toString());
                        singletonPackage.getInstance().switchActivity(menuItem.getTitle().toString());
                        cDrawerLayout.closeDrawers();
                        menuItem.setChecked(false);
                        return true;
                    }
                }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                cDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
