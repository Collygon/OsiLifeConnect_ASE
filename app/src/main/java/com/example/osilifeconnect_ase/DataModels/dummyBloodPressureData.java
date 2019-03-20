package com.example.osilifeconnect_ase.DataModels;

import java.util.Date;

public class dummyBloodPressureData extends dummyDataSuperClass {
    private Date date;
    private double dynostolic;
    private double systolic;
    private int pulseRate;

    public dummyBloodPressureData(int MRN){
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

    public Date getDate(){
        this.date=new Date();
        return this.date;
    }
}