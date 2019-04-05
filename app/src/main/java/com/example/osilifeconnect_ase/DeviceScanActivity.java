package com.example.osilifeconnect_ase;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

/**
 * This Activity scans for and displays available BLE devices
 */
public class DeviceScanActivity extends ListActivity {
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeScanner scanner;
    private boolean mScanning;
    private Handler handler;
    private LeDeviceListAdapter leDeviceListAdapter;

    private static final int REQUEST_ENABLE_BT = 1;
    //Stop scanning after 10 sec
    private static final long SCAN_PERIOD = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActionBar().setTitle(R.string.title_activity_device_scan);
        handler = new Handler();

        //check if device for BLE availability --Cullen
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
            Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
            finish();
        }

        //Initialize Bluetooth adapter
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();

        //Checks if Bluetooth is enable
        //If not says that Bluetooth is disabled and prompts user to change settings
        if(bluetoothAdapter == null || !bluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            int REQUEST_ENABLE_BT = 1;
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_ENABLE_BT){
            if(resultCode == RESULT_OK){
                Toast.makeText(this, "Bluetooth is now enabled", Toast.LENGTH_SHORT).show();
            }
            else if(resultCode == RESULT_CANCELED){
                Toast.makeText(this, "User did not enable Bluetooth or an error occurred", Toast.LENGTH_SHORT).show();
            }
        }
    }


    //...
    // Device scan callback.
    private ScanCallback leScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);

            if(result == null || result.getDevice() == null)
                return;

            leDeviceListAdapter.addDevice(result.getDevice());
            leDeviceListAdapter.notifyDataSetChanged();
        }
    };


    //...
    private void scanLeDevice(final boolean enable){
        if(enable){
            scanner = bluetoothAdapter.getBluetoothLeScanner();
            //stops scanning after a pre-defined scan period
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    scanner.stopScan(leScanCallback);
                }
            }, SCAN_PERIOD);

            mScanning = true;
            scanner.startScan(leScanCallback);
        }else{
            mScanning = false;
            scanner.stopScan(leScanCallback);
        }
        //...
    }
    //...
}

