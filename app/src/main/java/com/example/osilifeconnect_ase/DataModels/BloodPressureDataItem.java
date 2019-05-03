package com.example.osilifeconnect_ase.DataModels;

/**
 * Represents data to be read from a blood pressure sensor.
 */
public class BloodPressureDataItem extends SensorDataItem {
    private double dystolic; //val1
    private double systolic; //val2
    private double pulse; //val3
    private String dateString;

    public BloodPressureDataItem(String MRN, String loginID, String deviceType, String manufacturer,
                                 String modelNumber, String serialNumber, String readingDateTime,
                                 double dystolic, double systolic, double pulse) {
        super(MRN, loginID, deviceType, manufacturer, modelNumber, serialNumber, readingDateTime);
        this.dystolic = dystolic;
        this.systolic = dystolic;
        this.pulse = pulse;
    }

    public BloodPressureDataItem(String MRN, String loginID, String deviceType, String manufacturer,
    String modelNumber, String serialNumber, String readingDateTime,
    String val1, String val2, String val3) {
        super(MRN, loginID, deviceType, manufacturer, modelNumber, serialNumber, readingDateTime);
        this.setDystolic(val1);
        this.setSystolic(val2);
        this.setPulse(val3);
    }

    public double getDystolic() {
        return dystolic;
    }

    public double getSystolic() {
        return systolic;
    }

    public double getPulse() {
        return pulse;
    }


    public void setDystolic(String sVal) {
        double newVal;
        try{
            newVal = Double.parseDouble(sVal);
        } catch(Exception e){
            newVal = dNull;
        }
        this.dystolic = newVal;
    }

    public void setSystolic(String sVal) {
        double newVal;
        try{
            newVal = Double.parseDouble(sVal);
        } catch(Exception e){
            newVal = dNull;
        }
        this.systolic = newVal;
    }

    public void setPulse(String sVal) {
        double newVal;
        try{
            newVal = Double.parseDouble(sVal);
        } catch(Exception e){
            newVal = dNull;
        }
        this.pulse = newVal;
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
        return  "Blood Pressure Data: " + super.toString() +
                ", dystolic='" + getValString(this.dystolic) + '\'' +
                ", systolic='" + getValString(this.systolic) + '\'' +
                ", pulse='" + getValString(this.pulse) + '\'' +
                '}';
    }
   // public void setDateString(String dateString){this.dateString=dateString;}

  //  public String getDateString(){return this.dateString;}

}
