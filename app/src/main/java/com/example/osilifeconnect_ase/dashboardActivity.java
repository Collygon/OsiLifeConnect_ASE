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

    private NavigationView navigationView;
    private DrawerLayout cDrawerLayout;
    private Toolbar toolbar;
    private ActionBar actionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DASHBOARD", "Dashboard successfully opened.");
        super.onCreate(savedInstanceState);
        Log.d("DASHBOARD", "Instance State completed.");
        setContentView(R.layout.activity_dashboard);
        Log.d("DASHBOARD", "End of dashboard creation.");
        initializeComponents();
        
        
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        menuItem.setChecked(true);
                        Log.d("MENU ITEM", "Selected" + menuItem.getTitle().toString());
                        singletonPackage.getInstance().switchActivity(menuItem.getTitle().toString(), this);
                        menuItem.setChecked(false);
                        cDrawerLayout.closeDrawers();
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

    public void initializeComponents(){
        this.cDrawerLayout = findViewById(R.id.drawer_layout);
        this.navigationView = findViewById(R.id.nav_view);
        this.toolbar = findViewById(R.id.toolbar);
        this.actionbar = getSupportActionBar();
        this.actionbar.setDisplayHomeAsUpEnabled(true);
        this.actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
    }
}
