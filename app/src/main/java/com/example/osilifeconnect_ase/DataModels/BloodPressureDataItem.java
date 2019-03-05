package com.example.osilifeconnect_ase.DataModels;

public class BloodPressureDataItem extends SensorDataItem {
    private int val1;
    private int val2;
    private int val3;
    private int val4;
    private int val5;
    private int val6;
    private int val7;
    private int val8;
    private int val9;
    private int val10;
    private int val11;
    private int val12;

    public BloodPressureDataItem(String MRN, String recordFName, String recordLName, String deviceType, String manufacturer,
    String modelNumber, String serialNumber, String postingDateTime, String readingDateTime,
    String val1, String val2, String val3, String val4, String val5, String val6, String val7,
    String val8, String val9, String val10, String val11, String val12) {
        super(MRN, recordFName, recordLName, deviceType, manufacturer, modelNumber, serialNumber, postingDateTime, readingDateTime);
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

    public BloodPressureDataItem(String[] args){
        this(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10],
                args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18], args[19], args[20]);
    }

    public int getVal1() {
        return val1;
    }

    public void setVal1(int val1) {
        this.val1 = val1;
    }

    public int getVal2() {
        return val2;
    }

    public void setVal2(int val2) {
        this.val2 = val2;
    }

    public int getVal3() {
        return val3;
    }

    public void setVal3(int val3) {
        this.val3 = val3;
    }

    public int getVal4() {
        return val4;
    }

    public void setVal4(int val4) {
        this.val4 = val4;
    }

    public int getVal5() {
        return val5;
    }

    public void setVal5(int val5) {
        this.val5 = val5;
    }

    public int getVal6() {
        return val6;
    }

    public void setVal6(int val6) {
        this.val6 = val6;
    }

    public int getVal7() {
        return val7;
    }

    public void setVal7(int val7) {
        this.val7 = val7;
    }

    public int getVal8() {
        return val8;
    }

    public void setVal8(int val8) {
        this.val8 = val8;
    }

    public int getVal9() {
        return val9;
    }

    public void setVal9(int val9) {
        this.val9 = val9;
    }

    public int getVal10() {
        return val10;
    }

    public void setVal10(int val10) {
        this.val10 = val10;
    }

    public int getVal11() {
        return val11;
    }

    public void setVal11(int val11) {
        this.val11 = val11;
    }

    public int getVal12() {
        return val12;
    }

    public void setVal12(int val12) {
        this.val12 = val12;
    }

    public void setVal1(String sVal) {
        int newVal;
        try{
            newVal = Integer.parseInt(sVal);
        } catch(Exception e){
            newVal = iNull;
        }
        this.val1 = newVal;
    }

    public void setVal2(String sVal) {
        int newVal;
        try{
            newVal = Integer.parseInt(sVal);
        } catch(Exception e){
            newVal = iNull;
        }
        this.val2 = newVal;
    }

    public void setVal3(String sVal) {
        int newVal;
        try{
            newVal = Integer.parseInt(sVal);
        } catch(Exception e){
            newVal = iNull;
        }
        this.val3 = newVal;
    }

    public void setVal4(String sVal) {
        int newVal;
        try{
            newVal = Integer.parseInt(sVal);
        } catch(Exception e){
            newVal = iNull;
        }
        this.val4 = newVal;
    }

    public void setVal5(String sVal) {
        int newVal;
        try{
            newVal = Integer.parseInt(sVal);
        } catch(Exception e){
            newVal = iNull;
        }
        this.val5 = newVal;
    }

    public void setVal6(String sVal) {
        int newVal;
        try{
            newVal = Integer.parseInt(sVal);
        } catch(Exception e){
            newVal = iNull;
        }
        this.val6 = newVal;
    }

    public void setVal7(String sVal) {
        int newVal;
        try{
            newVal = Integer.parseInt(sVal);
        } catch(Exception e){
            newVal = iNull;
        }
        this.val7 = newVal;
    }

    public void setVal8(String sVal) {
        int newVal;
        try{
            newVal = Integer.parseInt(sVal);
        } catch(Exception e){
            newVal = iNull;
        }
        this.val8 = newVal;
    }

    public void setVal9(String sVal) {
        int newVal;
        try{
            newVal = Integer.parseInt(sVal);
        } catch(Exception e){
            newVal = iNull;
        }
        this.val9 = newVal;
    }

    public void setVal10(String sVal) {
        int newVal;
        try{
            newVal = Integer.parseInt(sVal);
        } catch(Exception e){
            newVal = iNull;
        }
        this.val10 = newVal;
    }

    public void setVal11(String sVal) {
        int newVal;
        try{
            newVal = Integer.parseInt(sVal);
        } catch(Exception e){
            newVal = iNull;
        }
        this.val11 = newVal;
    }

    public void setVal12(String sVal) {
        int newVal;
        try{
            newVal = Integer.parseInt(sVal);
        } catch(Exception e){
            newVal = iNull;
        }
        this.val12 = newVal;
    }

    public String getValString(int val) {
        String retStr = "";
        if (val == iNull) {
            retStr = sNull;
        } else {
            retStr = "" + val;
        }
        return retStr;
    }

    @Override
    public String toString() {
        return  "Blood Pressure Data: " + super.toString() +
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
