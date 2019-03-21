package com.example.osilifeconnect_ase.DataModels;
import java.util.Date;

public class dummyDataWeight extends dummyDataSuperClass {
    private double weightLbs;
    private double weightKgs;
    private Date date;

    public dummyDataWeight(int MRN){
        super(MRN);

    }
    public void setWeightLbs(int weight){
        this.weightLbs=weight;
    }

    public double getWeightLbs(){
        return this.weightLbs;
    }

    public void setWeightKgs(int weight){
        this.weightKgs=weight * .45359237;
    }

    public double getWeightKgs(){return this.weightKgs;}

    public Date getDate(){
        this.date=new Date();
        return this.date;
    }

}