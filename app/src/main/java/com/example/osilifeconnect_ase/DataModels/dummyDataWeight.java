package com.example.osilifeconnect_ase.DataModels;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dummyDataWeight extends dummyDataSuperClass {
    private double weightLbs;
    private double weightKgs;
    private Date date;

    public dummyDataWeight(int MRN){
        super(MRN);

    }
    public void setWeightLbs(double weight){
        this.weightLbs=weight;
    }

    public double getWeightLbs(){
        return this.weightLbs;
    }

    public void setWeightKgs(double weight){
        this.weightKgs=weight * .45359237;
    }

    public double getWeightKgs(){return this.weightKgs;}

    public void setDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return this.date;
    }

    public String getDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy hh:mm");
        return dateFormat.format(date);
    }

}