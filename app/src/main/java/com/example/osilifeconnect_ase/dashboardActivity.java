package com.example.osilifeconnect_ase;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

public class dashboardActivity extends AppCompatActivity {

    private Context context;

    // Layout that holds the menu items and manages the navigation menu
    private DrawerLayout cDrawerLayout;

    // Boolean for the switch view to determine if the user chose a menuItem or not.
    private boolean bSwitchCheck;


    /****************************************************
     * Generates the view for the dashboardActivity.
     * This activity is responsible for managing the action tool bar on the left side of the application.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        cDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        final TextView welcomeText = (TextView) findViewById(R.id.welcomeTextView);

        /****************
         * Applies a listener for the tool bar.
         * Listens for a user action, opening the bar from the left side of the screen.
         * Keeps the bar open until the user clicks out of it, or selects a 'menu item'
         * On selecting a menu item, the view changes with the use of fragments.
         */
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        menuItem.setChecked(true);
                        menuItem.setChecked(false);
                        cDrawerLayout.closeDrawers();
                        welcomeText.setVisibility(View.GONE);
                        bSwitchCheck = switchFragment(menuItem);
                        if(!bSwitchCheck)
                            return false;
                        return true;
                    }
                }
        );

    }

    /********************
     * Native function by Android Studio that is required for toolbar functionality.
     * Used to expand the toolbar and display all the menu items.
     * Details on the menu items can be found in: res/menu/navigation_menu.xml
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                cDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*************
     * Changes the view by loading a new fragment.
     * Called by the 'Switch Fragment' function
     * Uses an animation for the view change.
     * Details for these animations are found in: res/anim/...
     * @param fragment
     * @return boolean; required for the listener to function properly
     */
    public boolean loadFragment(Fragment fragment){
        if(fragment != null){

            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_right_to_left)
                    .replace(R.id.content_frame, fragment)
                    .commit();

            return true;
        }

        return false;
    }

    /**************
     * Called by the listener when a menu item is selected by the user in the
     * navigation tool bar.
     * Generates a new fragment which is passed to the load fragment button to change the view.
     * @param item [MenuItem]
     * @return boolean
     */
    public boolean switchFragment(MenuItem item){

        Fragment fragment = null;

        switch(item.getItemId()){
            case R.id.nav_account:
                fragment = new ProfileFragment();
                break;

            case R.id.nav_contact:
                fragment = new ContactFragment();
                break;

            case R.id.nav_devices:
                fragment = new DevicesFragment();
                break;

            case R.id.nav_settings:
                fragment = new SettingsFragment();
                break;

            default:
                return false;
        }

        return loadFragment(fragment);
    }
}
