package com.example.osilifeconnect_ase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DevicesFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_devices, null);
    }

    public void goToWeight (){
        Intent intent = new Intent(DevicesFragment.this.getActivity(), WeightActivity.class);
        startActivity(intent);
    }

    public void goToBlood(){
        Intent intent = new Intent(DevicesFragment.this.getActivity(), BloodPressureActivity.class);
        startActivity(intent);
    }
}
