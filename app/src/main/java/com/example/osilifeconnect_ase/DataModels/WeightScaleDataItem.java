package com.example.osilifeconnect_ase.DataModels;

//import java.time.LocalDateTime;

public class WeightScaleDataItem extends SensorDataItem {
    private double val1;
    private double val2;
    private double val3;
    private double val4;
    private double val5;
    private double val6;
    private double val7;
    private double val8;
    private double val9;
    private double val10;
    private double val11;
    private double val12;

    public WeightScaleDataItem(String MRN, String recordFName, String recordLName, String deviceType, String manufacturer,
                               String modelNumber, String serialNumber, String postingDateTime, String readingDateTime){
        super(MRN, recordFName, recordLName, deviceType, manufacturer, modelNumber, serialNumber, postingDateTime, readingDateTime);
        this.val1 = dNull;
        this.val2 = dNull;
        this.val3 = dNull;
        this.val4 = dNull;
        this.val5 = dNull;
        this.val6 = dNull;
        this.val7 = dNull;
        this.val8 = dNull;
        this.val9 = dNull;
        this.val10 = dNull;
        this.val11 = dNull;
        this.val12 = dNull;
    }

    public WeightScaleDataItem(String MRN, String recordFName, String recordLName, String deviceType, String manufacturer,
                               String modelNumber, String serialNumber, String postingDateTime, String readingDateTime,
                               String val1, String val2, String val3, String val4, String val5, String val6, String val7,
                               String val8, String val9, String val10, String val11, String val12) {
        this(MRN, recordFName, recordLName, deviceType, manufacturer, modelNumber, serialNumber, postingDateTime, readingDateTime);
        this.setVal1(val1);
        this.setVal2(val2);
        this.setVal3(val3);
        this.setVal4(val4);
        this.setVal5(val5);
        this.setVal6(val6);
        this.setVal7(val7);
        this.setVal8(val8);
        this.setVal9(val9);
        this.setVal10(val10);
        this.setVal11(val11);
        this.setVal12(val12);
    }

    public WeightScaleDataItem(String[] args){
        this(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10],
                args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18], args[19], args[20]);
    }


    public double getVal1() {
        return val1;
    }

    public void setVal1(double val1) {
        this.val1 = val1;
    }

    public double getVal2() {
        return val2;
    }

    public void setVal2(double val2) {
        this.val2 = val2;
    }

    public double getVal3() {
        return val3;
    }

    public void setVal3(double val3) {
        this.val3 = val3;
    }

    public double getVal4() {
        return val4;
    }

    public void setVal4(double val4) {
        this.val4 = val4;
    }

    public double getVal5() {
        return val5;
    }

    public void setVal5(double val5) {
        this.val5 = val5;
    }

    public double getVal6() {
        return val6;
    }

    public void setVal6(double val6) {
        this.val6 = val6;
    }

    public double getVal7() {
        return val7;
    }

    public void setVal7(double val7) {
        this.val7 = val7;
    }

    public double getVal8() {
        return val8;
    }

    public void setVal8(double val8) {
        this.val8 = val8;
    }

    public double getVal9() {
        return val9;
    }

    public void setVal9(double val9) {
        this.val9 = val9;
    }

    public double getVal10() {
        return val10;
    }

    public void setVal10(double val10) {
        this.val10 = val10;
    }

    public double getVal11() {
        return val11;
    }

    public void setVal11(double val11) {
        this.val11 = val11;
    }

    public double getVal12() {
        return val12;
    }

    public void setVal12(double val12) {
        this.val12 = val12;
    }

    public void setVal1(String sVal) {
        double newVal;
        try{
            newVal = Double.parseDouble(sVal);
        } catch(Exception e){
            newVal = dNull;
        }
        this.val1 = newVal;
    }

    public void setVal2(String sVal) {
        double newVal;
        try{
            newVal = Double.parseDouble(sVal);
        } catch(Exception e){
            newVal = dNull;
        }
        this.val2 = newVal;
    }

    public void setVal3(String sVal) {
        double newVal;
        try{
            newVal = Double.parseDouble(sVal);
        } catch(Exception e){
            newVal = dNull;
        }
        this.val3 = newVal;
    }

    public void setVal4(String sVal) {
        double newVal;
        try{
            newVal = Double.parseDouble(sVal);
        } catch(Exception e){
            newVal = dNull;
        }
        this.val4 = newVal;
    }

    public void setVal5(String sVal) {
        double newVal;
        try{
            newVal = Double.parseDouble(sVal);
        } catch(Exception e){
            newVal = dNull;
        }
        this.val5 = newVal;
    }

    public void setVal6(String sVal) {
        double newVal;
        try{
            newVal = Double.parseDouble(sVal);
        } catch(Exception e){
            newVal = dNull;
        }
        this.val6 = newVal;
    }

    public void setVal7(String sVal) {
        double newVal;
        try{
            newVal = Double.parseDouble(sVal);
        } catch(Exception e){
            newVal = dNull;
        }
        this.val7 = newVal;
    }

    public void setVal8(String sVal) {
        double newVal;
        try{
            newVal = Double.parseDouble(sVal);
        } catch(Exception e){
            newVal = dNull;
        }
        this.val8 = newVal;
    }

    public void setVal9(String sVal) {
        double newVal;
        try{
            newVal = Double.parseDouble(sVal);
        } catch(Exception e){
            newVal = dNull;
        }
        this.val9 = newVal;
    }

    public void setVal10(String sVal) {
        double newVal;
        try{
            newVal = Double.parseDouble(sVal);
        } catch(Exception e){
            newVal = dNull;
        }
        this.val10 = newVal;
    }

    public void setVal11(String sVal) {
        double newVal;
        try{
            newVal = Double.parseDouble(sVal);
        } catch(Exception e){
            newVal = dNull;
        }
        this.val11 = newVal;
    }

    public void setVal12(String sVal) {
        double newVal;
        try{
            newVal = Double.parseDouble(sVal);
        } catch(Exception e){
            newVal = dNull;
        }
        this.val12 = newVal;
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
                ", val1='" + getValString(this.val1) + '\'' +
                ", val2='" + getValString(this.val2) + '\'' +
                ", val3='" + getValString(this.val3) + '\'' +
                ", val4='" + getValString(this.val4) + '\'' +
                ", val5='" + getValString(this.val5) + '\'' +
                ", val6='" + getValString(this.val6) + '\'' +
                ", val7='" + getValString(this.val7) + '\'' +
                ", val8='" + getValString(this.val8) + '\'' +
                ", val9='" + getValString(this.val9) + '\'' +
                ", val10='" + getValString(this.val10) + '\'' +
                ", val11='" + getValString(this.val11) + '\'' +
                ", val12='" + getValString(this.val12) + '\'' +
                '}';
    }


}
