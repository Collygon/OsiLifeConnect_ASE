package com.example.osilifeconnect_ase;

import android.bluetooth.BluetoothDevice;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
/**
class LeDeviceListAdapter extends BaseAdapter {
    private ArrayList<BluetoothDevice> leDevices;
    private LayoutInflater inflater;

    public LeDeviceListAdapter(){
        super();
        leDevices = new ArrayList<BluetoothDevice>();

    }

    public void addDevice(BluetoothDevice device) {
        if(!leDevices.contains(device))
            leDevices.add(device);
    }

    public BluetoothDevice getDevice(int position){
        return leDevices.get(position);
    }

    public void notifyDataSetChanged() {
    }

    public void clear() {
        leDevices.clear();
    }

    @Override
    public int getCount() {
        return leDevices.size();
    }

    @Override
    public Object getItem(int i) {
        return leDevices.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if(view == null){
            view = inflater.inflate(R.layout.listitem_device, null);
            viewHolder = new ViewHolder();
            viewHolder.deviceName = (TextView) view.findViewById(R.id.device_name);
            viewHolder.deviceAddress = (TextView) view.findViewById(R.id.device_address);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        BluetoothDevice device = leDevices.get(i);
        final String deviceName = device.getName();
        if(deviceName != null && deviceName.length() > 0)
            viewHolder.deviceName.setText(deviceName);
        else
            viewHolder.deviceName.setText("Unknown Device");
        viewHolder.deviceAddress.setText(device.getAddress());

        return view;
    }

    static class ViewHolder{
        TextView deviceName;
        TextView deviceAddress;
    }
}*/
