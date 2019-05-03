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

public class DevicesFragment extends Fragment{


    /*****************************************
     * Standard onCreate method required by Android Studio.
     * This fragment view is responsible for holding the buttons that open the activity that displays the info
     * as well as allowing the scan functionality.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_devices, container, false);

        Button bpButton = view.findViewById(R.id.dashbuttonBlood);
        Button wButton = view.findViewById(R.id.dashbuttonWeight);
        Button sButton = view.findViewById(R.id.dashbuttonScan);

        /*****************************
         * Listener for the button that loads the blood pressure view.
         */
        bpButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                goToBlood(v);
            }
        });

        /**************************
         * Listener for the button that loads the weight view
         */
        wButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                goToWeight(v);
            }
        });

        /***************************************88
         * Listener for the button that initializes the bluetooth scan
         */
        sButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                scan();
            }
        });

        return view;
    }

    /********************************
     * Loads the weight view activity
     * @param view
     */
    public void goToWeight (View view){
        Intent intent = new Intent(DevicesFragment.this.getActivity(), WeightActivity.class);
        startActivity(intent);
    }

    /***********************************
     * Loads the blood pressure view activity
     * @param view
     */
    public void goToBlood(View view){
        Intent intent = new Intent(DevicesFragment.this.getActivity(), BloodPressureActivity.class);
        startActivity(intent);
    }

    /*****************************************
     * Loads the scan activity, starting the bluetooth scan
     */
    public void scan(){
        Intent intent = new Intent(DevicesFragment.this.getActivity(), DeviceScanActivity.class);
        startActivity(intent);
    }
}
