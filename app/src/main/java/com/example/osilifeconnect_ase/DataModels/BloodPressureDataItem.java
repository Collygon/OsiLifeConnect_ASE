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

    public double getDystolic() {
        return dystolic;
    }

    public double getSystolic() {
        return systolic;
    }

    public double getPulse() {
        return pulse;
    }


    @Override
    public String toString() {
        return  "Blood Pressure Data: " + super.toString() +
                ", dystolic='" + this.dystolic + '\'' +
                ", systolic='" + this.systolic + '\'' +
                ", pulse='" + this.pulse + '\'' +
                '}';
    }
   // public void setDateString(String dateString){this.dateString=dateString;}

  //  public String getDateString(){return this.dateString;}

}
