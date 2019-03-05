package com.example.osilifeconnect_ase.Gateways;

import com.example.osilifeconnect_ase.DataModels.BloodPressureDataItem;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BloodPressureGateway {
    private InputStream inputStream;

    public BloodPressureGateway(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public List<BloodPressureDataItem> getCompleteList() {
        List<BloodPressureDataItem> items = new ArrayList<>();
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String lineItem = "";
            while((lineItem = buffReader.readLine()) != null){
                String[] tokens = lineItem.split(",");
                items.add(new BloodPressureDataItem(tokens));
            }
        } catch (IOException e){
            throw new RuntimeException("Error reading blood pressure test data");
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException e){
                throw new RuntimeException("Error while closing InputStream in blood pressure gateway");
            }
        }


        return items;
    }

    public List<BloodPressureDataItem> getListByMRN(String MRN){
        List<BloodPressureDataItem> items = new ArrayList<>();
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String lineItem = "";
            while((lineItem = buffReader.readLine()) != null){
                String[] tokens = lineItem.split(",");
                if(tokens[0].equalsIgnoreCase(MRN)) {
                    items.add(new BloodPressureDataItem(tokens));
                }
            }
        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Error reading blood pressure test data");
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException e){
                e.printStackTrace();
                throw new RuntimeException("Error while closing InputStream in bloodpressure gateway");
            }
        }

        return items;
    }



}
