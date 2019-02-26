package com.example.osilifeconnect_ase.Gateways;

import com.example.osilifeconnect_ase.DataModels.WeightScaleDataItem;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WeightScaleGateway {
    private InputStream inputStream;

    public WeightScaleGateway(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public List<WeightScaleDataItem> getCompleteList() {
        List<WeightScaleDataItem> items = new ArrayList<>();
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String lineItem = "";
            while((lineItem = buffReader.readLine()) != null){
                String[] tokens = lineItem.split(",");
                items.add(new WeightScaleDataItem(tokens));
            }
        } catch (IOException e){
            throw new RuntimeException("Error reading WeightScale test data");
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException e){
                throw new RuntimeException("Error while closing InputStream in weigthscale gateway");
            }
        }


        return items;
    }

    public List<WeightScaleDataItem> getListByMRN(String MRN){
        List<WeightScaleDataItem> items = new ArrayList<>();
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String lineItem = "";
            while((lineItem = buffReader.readLine()) != null){
                String[] tokens = lineItem.split(",");
                if(tokens[0].equalsIgnoreCase(MRN)) {
                    items.add(new WeightScaleDataItem(tokens));
                }
            }
        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Error reading WeightScale test data");
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException e){
                e.printStackTrace();
                throw new RuntimeException("Error while closing InputStream in weigthscale gateway");
            }
        }

        return items;
    }

}

