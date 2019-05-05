package com.example.osilifeconnect_ase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/*******************************
 * This fragment holds the information for getting in contact with Osi Vision.
 * It displays a google maps view with the address and shows other information,
 * phone number, email, and website.
 */
public class ContactFragment extends Fragment implements OnMapReadyCallback {

    SupportMapFragment mapFragment;
    GoogleMap mMap;

    /********************************
     * Standard create function required by Android Studio.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.osiMap);
        if (mapFragment == null){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.osiMap, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //MapsInitializer.initialize(getContext());
        mMap = googleMap;

        LatLng latLng = new LatLng(29.5411298, -98.4908064);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        markerOptions.title("Osi Life");
        markerOptions.snippet("Osi Life");

        float zoomLevel = 16.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
        //Location location = LocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        mMap.addMarker(markerOptions);
    }
}
