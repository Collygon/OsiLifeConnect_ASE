package com.example.osilifeconnect_ase.DataModels;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Represents data to be read from a sensor.
 */
public class SensorDataItem {
    protected static double dNull = -9999.0;
    protected static String sNull = "NULL";
    private String MRN;
    private String loginID;
    private String DeviceType;
    private String Manufacturer;
    private String modelNumber;
    private String SerialNumber;
    //private String readingDateTime;
    private String readingDateTime;

    public SensorDataItem(String MRN, String loginID, String deviceType, String manufacturer,
                          String modelNumber, String serialNumber, String readingDateTime) {
        this.MRN = MRN;
        this.loginID = loginID;
        this.DeviceType = deviceType;
        this.Manufacturer = manufacturer;
        this.modelNumber = modelNumber;
        this.SerialNumber = serialNumber;
        this.readingDateTime = readingDateTime;
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
        this.MRN = MRN;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
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

    public String getReadingDateTime() {
        return readingDateTime;
    }

    public void setReadingDateTime(String readingDateTime) {
        this.readingDateTime = readingDateTime;
    }

    @Override
    public String toString() {
        return  "MRN='" + MRN + '\'' +
                ", DeviceType='" + DeviceType + '\'' +
                ", Manufacturer='" + Manufacturer + '\'' +
                ", modelNumber='" + modelNumber + '\'' +
                ", SerialNumber='" + SerialNumber + '\'' +
                ", ReadingDateTime='" + readingDateTime.toString() + '\'' +
                '}';
    }
}
