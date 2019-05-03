package com.example.osilifeconnect_ase.DataModels;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dummyBloodPressureData extends dummyDataSuperClass {
    private Date date;
    private double dynostolic;
    private double systolic;
    private int pulseRate;
    private String dateString;

    public dummyBloodPressureData(String MRN){
        super(MRN);
    }
    public void setDynostolic(double dynostolic){
        this.dynostolic=dynostolic;
    }

    public double getDynostolic() {
        return this.dynostolic;
    }

    public void setSystolic(double systolic){
        this.systolic=systolic;
    }

    public double getSystolic(){
        return this.systolic;
    }

    public void setPulseRate(int pulseRate){
        this.pulseRate=pulseRate;
    }

    public int getPulseRate(){
        return this.pulseRate;
    }

    public void setDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return this.date;
    }

    public String getDateToString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy hh:mm");
        return dateFormat.format(date);
    }

    public void setDateString(String dateString){this.dateString=dateString;}

      public String getDateString(){return this.dateString;}
}