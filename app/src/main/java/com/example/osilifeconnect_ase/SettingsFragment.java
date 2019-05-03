package com.example.osilifeconnect_ase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/***************************************************************
 * This fragment stores the buttons for loading different setting options.
 * Units changes the type of units displayed on the sensor views.
 * Configuration has general UI changes for the user.
 */
public class SettingsFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Button uButton = view.findViewById(R.id.unitsButton);
        Button cButton = view.findViewById(R.id.configButton);

        /************************
         * Listener for the Units Settings button
         */
        uButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                goToUnits(v);
            }
        });

        /************************
         * Listener for the Configuration Settings button
         */
        cButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                goToConfiguration(v);
            }
        });

        return view;
    }

    /*****************************
     * Loads the Units settings activity view
     * @param view
     */
    public void goToUnits(View view){
        Intent intent = new Intent(SettingsFragment.this.getActivity(), UnitsActivity.class);
        startActivity(intent);
    }

    /*****************************
     * Loads the Configuration settings activity view
     * @param view
     */
    public void goToConfiguration(View view){
        Intent intent = new Intent(SettingsFragment.this.getActivity(), ConfigurationActivity.class);
        startActivity(intent);
    }
}
