package com.example.osilifeconnect_ase;

import android.bluetooth.BluetoothDevice;

public class BloodPressureDevice {
    private static final BloodPressureDevice ourInstance = new BloodPressureDevice();
    private BluetoothDevice device;

    public static BloodPressureDevice getInstance() {
        return ourInstance;
    }

    private BloodPressureDevice() {
    }

    public void setDevice(BluetoothDevice device) {
        this.device = device;
    }

    public BluetoothDevice getDevice(){
        return this.device;
    }
}
