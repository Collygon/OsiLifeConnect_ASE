package com.example.osilifeconnect_ase.DataModels;

//import java.time.LocalDateTime;

import java.sql.Timestamp;

/**
 * Represents data to be read from a weight scale sensor.
 */
public class WeightScaleDataItem extends SensorDataItem {
    private double weight; //val1

    public WeightScaleDataItem(String MRN, String loginID, String deviceType, String manufacturer,
                               String modelNumber, String serialNumber, Timestamp readingDateTime,
                               double weight){
        super(MRN, loginID, deviceType, manufacturer, modelNumber, serialNumber, readingDateTime);
        this.weight = weight;
    }

    public WeightScaleDataItem(String MRN, String loginID, String deviceType, String manufacturer,
                               String modelNumber, String serialNumber, Timestamp readingDateTime,
                               String weight){
        super(MRN, loginID, deviceType, manufacturer, modelNumber, serialNumber, readingDateTime);
        this.setDystolic(weight);
    }


    public double getWeight() {
        return weight;
    }

    public void setDystolic(String sVal) {
        double newVal;
        try {
            newVal = Double.parseDouble(sVal);
        } catch (Exception e) {
            newVal = dNull;
        }
        this.weight = newVal;
    }

    public String getValString(double val) {
        String retStr = "";
        if (val == dNull) {
            retStr = sNull;
        } else {
            retStr = "" + val;
        }
        return retStr;
    }

    @Override
    public String toString() {
        return  "Weight Scale Data: " + super.toString() +
                ", weight='" + getValString(this.weight) + '\'' +
                '}';
    }


}
