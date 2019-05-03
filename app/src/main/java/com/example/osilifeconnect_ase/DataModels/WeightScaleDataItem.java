package com.example.osilifeconnect_ase.DataModels;

//import java.time.LocalDateTime;

import java.sql.Timestamp;

/**
 * Represents data to be read from a weight scale sensor.
 */
public class WeightScaleDataItem extends SensorDataItem {
    private double weight; //val1

    public WeightScaleDataItem(String MRN, String loginID, String deviceType, String manufacturer,
                               String modelNumber, String serialNumber, String readingDateTime,
                               double weight){
        super(MRN, loginID, deviceType, manufacturer, modelNumber, serialNumber, readingDateTime);
        this.weight = weight;
    }


    public double getWeight() {
        return weight;
    }


    @Override
    public String toString() {
        return  "Weight Scale Data: " + super.toString() +
                ", weight='" + this.weight + '\'' +
                '}';
    }


}
