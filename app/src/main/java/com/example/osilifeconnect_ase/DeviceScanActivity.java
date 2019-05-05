package com.example.osilifeconnect_ase;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This Activity scans for and displays available BLE devices.
 * Currently it is hardcoded to "filter" for the MAC Address
 * of the Blood Pressure device with a simple if statement.
 * Ideally it should probably use an actual ScanFilter instead.
 *
 */
public class DeviceScanActivity extends ListActivity {
    private String bpMAC = "64:CF:D9:36:C9:90";
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeScanner scanner;
    private boolean mScanning;
    private Handler handler;
    private LeDeviceListAdapter leDeviceListAdapter;
    private ArrayList<BluetoothDevice> leDevices;

    private static final int REQUEST_ENABLE_BT = 1;
    //Stop scanning after 1 minute
    private static final long SCAN_PERIOD = 60000;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //getActionBar().setTitle(R.string.title_activity_device_scan);
       // ListView list = new ListView(this);
        leDeviceListAdapter = new LeDeviceListAdapter(this);
        //list.setAdapter(leDeviceListAdapter);
        //setContentView(list);
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
            finish();
        }
        leDevices = new ArrayList<BluetoothDevice>();
    }

    /**
     * These blocks are from Android Bluetooth LE sample code and
     * were kept around just in case.
     * As the app currently operates, these can be safely removed.
     */
    /********
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menu_scan:
                leDeviceListAdapter.clear();
                scanLeDevice(true);
                break;
            case R.id.menu_stop:
                scanLeDevice(false);
                break;
        }
        return true;
    }*******/

    @Override
    protected void onResume(){
        super.onResume();

        if(!bluetoothAdapter.isEnabled()){
            if(!bluetoothAdapter.isEnabled()){
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                finish();
            }
        }

        leDeviceListAdapter = new LeDeviceListAdapter(this);
        setListAdapter(leDeviceListAdapter);
        scanLeDevice(true);
    }

    @Override
    protected void onPause(){
        super.onPause();
        scanLeDevice(false);
        scanComplete();
        leDeviceListAdapter.clear();
    }


    // part of the LeDeviceListAdapter way of doing things.
    /*@Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        final BluetoothDevice device = leDeviceListAdapter.getDevice(position);
        if(device == null)
            return;
        final Intent intent = new Intent(this, DeviceControlActivity.class);
        intent.putExtra(DeviceControlActivity.LIST_NAME, device.getName());
        intent.putExtra(DeviceControlActivity.LIST_UUID, device.getAddress());
        if(mScanning) {
            scanner.stopScan(leScanCallback);
            mScanning = false;
        }

        startActivity(intent);
    }*/

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

    // Device scan callback.
    private ScanCallback leScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);

            if(result == null || result.getDevice() == null)
                return;

            processResult(result);
            Log.d("Device Name", "onScanResult: " + result.getDevice().getName());
        }
    };

    /**
     * Do something with the result grabbed by the Bluetooth Scan.
     * Currently we grab the device we scanned and try to match its
     * MAC address as an improvised filter. When we match, the app
     * connects to the that device (only the Blood Pressure as of now)
     * and takes the user to the Device Control Activity.
     *
     * If the result is not one of the desired devices, the device
     * is added to the leDevices list but nothing happens.
     * (To reiterate, a real ScanFilter may be desired to properly ignore
     * other devices)
     *
     * @param result a Bluetooth signal picked up during scan
     */
    private void processResult(ScanResult result){
        // Grab device from result
        BluetoothDevice device = result.getDevice();
        Log.d("MAC:", device.getAddress());
        // Improvised filter. Does the device MAC match our desired device MAC?
        if(device.getAddress().equals(bpMAC)){
            Log.d("Result","processResult: Blood Pressure connected!");
            Intent bloodIntent = new Intent(this, DeviceControlActivity.class);
            bloodIntent.putExtra(DeviceControlActivity.EXTRAS_DEVICE_NAME, device.getName());
            bloodIntent.putExtra(DeviceControlActivity.EXTRAS_DEVICE_ADDRESS, device.getAddress());
            if (mScanning) {
                bluetoothAdapter.getBluetoothLeScanner().stopScan(leScanCallback);
                mScanning = false;
                scanComplete();
            }
            startActivity(bloodIntent);
        }
        leDevices.add(device);
    }


    /**
     * Sets up a BluetoothLeScanner and scans for a Bluetooth Low Energy device.
     * Scan lasts until the assigned SCAN_PERIOD or until some other function
     * calls scanner.stopScan.
     *
     * @param enable set to true or false when the call scanLeDevice() is made;
     */
    private void scanLeDevice(final boolean enable) {
        if (enable) {
            scanner = bluetoothAdapter.getBluetoothLeScanner();
            //stops scanning after a pre-defined scan period
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    scanner.stopScan(leScanCallback);
                    scanComplete();
                }
            }, SCAN_PERIOD);

            mScanning = true;
            scanner.startScan(leScanCallback);
        } else {
            mScanning = false;
            scanner.stopScan(leScanCallback);
        }
    }

    /**
     * A debug function.
     * Iterates through the list of devices and prints
     * their names and MAC addresses.
     */
    private void scanComplete(){
        if(leDeviceListAdapter.getCount() == 0)
            return;
        for(BluetoothDevice d : leDevices)
            Log.d("BLE", "Found Device: " + d.getName() +"\n"+ d.getAddress());
    }


    /**
     * The LeDeviceListAdapter is supposed to handle displaying
     * what devices the scanner picked up during its scan.
     * A list view would allow the user to pick what device to connect to,
     * but we could not get this function to work. Instead, the processResult function
     * automatically connects the user to whichever device they are using since a connection
     * can only happen once data is being transmitted.
     *
     * This code remains in case the original functionality is desired to be worked in.
     */
    private static class LeDeviceListAdapter extends ArrayAdapter<BluetoothDevice> {
        private LayoutInflater inflater;

        public LeDeviceListAdapter(Context context){
            super(context, 0);
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;

            if(view == null){
                view = inflater.inflate(R.layout.listitem_device, null);
            }

            BluetoothDevice device = getItem(i);
            viewHolder = new ViewHolder();
            viewHolder.deviceName = (TextView) view.findViewById(R.id.device_name);
            viewHolder.deviceAddress = (TextView) view.findViewById(R.id.device_address);
            view.setTag(viewHolder);

            viewHolder.deviceName.setText(device.getName());
            viewHolder.deviceAddress.setText(device.getAddress());

            return view;
        }
    }

    static class ViewHolder{
        TextView deviceName;
        TextView deviceAddress;
    }
}

