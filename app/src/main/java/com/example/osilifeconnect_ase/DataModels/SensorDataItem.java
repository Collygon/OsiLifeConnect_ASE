package com.example.osilifeconnect_ase.DataModels;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SensorDataItem {
    public static int iNull = -9999;
    public static double dNull = -9999.0;
    public static String sNull = "NULL";
    private String MRN;
    private String recordFName;
    private String recordLName;
    private String DeviceType;
    private String Manufacturer;
    private String modelNumber;
    private String SerialNumber;
    private String postingDateTime;
    private String readingDateTime;
    //private Timestamp postingDateTime;
    //private Timestamp readingDateTime;

    public SensorDataItem(String MRN, String recordFName, String recordLName, String deviceType,
                          String manufacturer, String modelNumber, String serialNumber, String postingDateTime, String readingDateTime) {
        this.setMRN(MRN);
        this.recordFName = recordFName;
        this.recordLName = recordLName;
        DeviceType = deviceType;
        Manufacturer = manufacturer;
        this.modelNumber = modelNumber;
        SerialNumber = serialNumber;
        this.setPostingDateTime(postingDateTime);
        this.setReadingDateTime(readingDateTime);
    }

    public int getiNull() {
        return iNull;
    }

    public double getdNull() {
        return dNull;
    }

    public String getsNull() {
        return sNull;
    }

    public String getMRN() {
        return MRN;
    }

    public void setMRN(String MRN) {
        int iTest;
        String newMRN = MRN;
        try{
            iTest = Integer.parseInt(MRN);
        } catch(Exception e){
            newMRN = sNull;
        }
        this.MRN = newMRN;
    }

    public String getRecordFName() {
        return recordFName;
    }

    public void setRecordFName(String recordFName) {
        this.recordFName = recordFName;
    }

    public String getRecordLName() {
        return recordLName;
    }

    public void setRecordLName(String recordLName) {
        this.recordLName = recordLName;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getPostingDateTime() {
        return postingDateTime;
    }

    public void setPostingDateTime(String postingDateTime) {
        //this.postingDateTime = Timestamp.valueOf(postingDateTime);
        this.postingDateTime = postingDateTime;


    }

    public String getReadingDateTime() {
        return readingDateTime;
    }

    public void setReadingDateTime(String readingDateTime) {
        //this.ReadingDateTime = Timestamp.valueOf(readingDateTime);
        this.readingDateTime = readingDateTime;
    }

    @Override
    public String toString() {
        return  "MRN='" + MRN + '\'' +
                ", recordFName='" + recordFName + '\'' +
                ", recordLName='" + recordLName + '\'' +
                ", DeviceType='" + DeviceType + '\'' +
                ", Manufacturer='" + Manufacturer + '\'' +
                ", modelNumber='" + modelNumber + '\'' +
                ", SerialNumber='" + SerialNumber + '\'' +
                ", postingDateTime='" + postingDateTime + '\'' +
                ", ReadingDateTime='" + readingDateTime + '\'' +
                '}';
    }
}
