package com.example.osilifeconnect_ase;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.osilifeconnect_ase.DataModels.dummyDataWeight;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class WeightActivity extends AppCompatActivity {
    private RecyclerView recView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Set<BluetoothDevice> pairedDevices;
    private BluetoothLeService bluetoothLeService;
    private BluetoothAdapter bluetoothAdapter;

    //create a bunch of dummyWeight objects
    dummyDataWeight weightObj1 = new dummyDataWeight(0001);
    dummyDataWeight weightObj2 = new dummyDataWeight(0002);
    dummyDataWeight weightObj3 = new dummyDataWeight(0003);
    dummyDataWeight weightObj4 = new dummyDataWeight(0004);
    dummyDataWeight weightObj5 = new dummyDataWeight(0005);
    dummyDataWeight weightObj6 = new dummyDataWeight(0006);

    //array lists to hold the dummy data
    private List<dummyDataWeight> dayList = new ArrayList<>();
    private List<dummyDataWeight> weekList = new ArrayList<>();
    private List<dummyDataWeight> monthList = new ArrayList<>();
    private WeightActivity bluetoothService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
            Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
            finish();
        }

        //Initialize Bluetooth adapter
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();
        bluetoothLeService = new BluetoothLeService();

        //Checks if Bluetooth is enable
        //If not says that Bluetooth is disabled and prompts user to change settings
        if(bluetoothAdapter == null || !bluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            int REQUEST_ENABLE_BT = 1;
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            finish();
        }
        setContentView(R.layout.activity_weight);

        //set dummy weights
        weightObj1.setWeightLbs(90.0);
        weightObj2.setWeightLbs(10.0);
        weightObj3.setWeightLbs(80.5);
        weightObj4.setWeightLbs(122.32);
        weightObj5.setWeightLbs(200.12);
        weightObj6.setWeightLbs(100.0);

        //add dummy vals to lists
        dayList.add(weightObj1);
        dayList.add(weightObj2);
        dayList.add(weightObj3);
        dayList.add(weightObj4);
        dayList.add(weightObj5);
        dayList.add(weightObj6);

        weekList.add(weightObj6);
        weekList.add(weightObj3);
        weekList.add(weightObj2);

        monthList.add(weightObj3);

        recView = findViewById(R.id.my_recycler_view);
        recView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recView.setLayoutManager(layoutManager);

        adapter = new WeightAdapter(dayList);
        recView.setAdapter(adapter);
    }

    public void getDayData(View view){
        //TODO display day data
        adapter = new WeightAdapter(dayList);
        recView.setAdapter(adapter);
    }

    public void getWeekData(View view) {
        //TODO display week data
        adapter = new WeightAdapter(weekList);
        recView.setAdapter(adapter);
    }
    public void getMonthData(View view){
        //TODO display month data
        adapter = new WeightAdapter(monthList);
        recView.setAdapter(adapter);
    }

    void getPairedDevices(){
        pairedDevices = bluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            ArrayList<String> deviceList = new ArrayList<>(pairedDevices.size());
            for (BluetoothDevice bluetoothDevice : pairedDevices) {
                BluetoothSensorDevice sensorDevice = new BluetoothSensorDevice(bluetoothDevice);
                deviceList.add(sensorDevice.getLabel());
            }
            final String[] deviceArray = deviceList.toArray(new String[0]);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Scale:");
            builder.setItems(deviceArray, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, final int item) {
                    BluetoothSensorDevice sensorDevice = BluetoothSensorDevice.fromLabel(deviceArray[item]);
                    connectToDevice(sensorDevice);
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public void connectToDevice(BluetoothSensorDevice sensorDevice) {
        //this.bluetoothLeService.connectToDevice(this.bluetoothAdapter.getRemoteDevice(sensorDevice.getAddress()));
    }
}
