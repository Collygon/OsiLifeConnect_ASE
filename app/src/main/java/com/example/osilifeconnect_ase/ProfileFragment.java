package com.example.osilifeconnect_ase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ProfileFragment extends Fragment{

    private Button logoutButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.logoutButton = logoutButton.findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                logoutMethod(v);
            }
        });

        return inflater.inflate(R.layout.fragment_profile, null);
    }

    public void logoutMethod(View v){
        Intent logIntent = new Intent(getActivity(), MainActivity.class);
        Log.d("PROFILE FRAGMENT", "Logging out");
        notificationPackage.getInstance().loginNotify(getActivity());
        startActivity(logIntent);
    }
}
