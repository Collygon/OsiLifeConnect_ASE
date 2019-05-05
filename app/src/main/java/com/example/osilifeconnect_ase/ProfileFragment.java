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

/*********************************************
 * This fragment is used for logging out and displaying relevant user data
 * Has a logout button, profile image, and physician information.
 * Methods of setting this information have not yet been implemented.
 */
public class ProfileFragment extends Fragment{

    private Button logoutButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, null);

        this.logoutButton = v.findViewById(R.id.logoutButton);

        /***************************************************8
         * Listener for the logout button.
         */
        logoutButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                logoutMethod(v);
            }
        });

        return v;
    }

    /**************************************************
     * Simply returns the user to the login view.
     * @param v
     */
    public void logoutMethod(View v){
        Intent logIntent = new Intent(getActivity(), MainActivity.class);
        Log.d("PROFILE FRAGMENT", "Logging out");
        notificationPackage.getInstance().loginNotify(getActivity());
        startActivity(logIntent);
    }
}
