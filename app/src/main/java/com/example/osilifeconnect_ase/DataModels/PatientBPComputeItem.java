package com.example.osilifeconnect_ase.DataModels;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Represents data from the blood pressure compute table
 */
public class PatientBPComputeItem {
    private String MRN;
    private String loginID;
    private Timestamp readingDateTime;
    private double age; //"=(Today-DOB)/365"
    private int weekNumber; //"=WEEKNUM(ReadingDate,1)"
    private String timeSlot; //"=IF(ReadingDateTime)=(0100-0900, AM-Initial),
    private double dystolic; //val1
    private double systolic; //val2
    private double pulse; //val3

    public PatientBPComputeItem(String MRN, String loginID, Timestamp readingDateTime, double age,
                                int weekNumber, String timeSlot, double dystolic, double systolic, double pulse) {
        this.MRN = MRN;
        this.loginID = loginID;
        this.readingDateTime = readingDateTime;
        this.age = age;
        this.weekNumber = weekNumber;
        this.timeSlot = timeSlot;
        this.dystolic = dystolic;
        this.systolic = systolic;
        this.pulse = pulse;
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

    public Timestamp getReadingDateTime() {
        return readingDateTime;
    }

    public void setReadingDateTime(Timestamp readingDateTime) {
        this.readingDateTime = readingDateTime;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public double getDystolic() {
        return dystolic;
    }

    public void setDystolic(double dystolic) {
        this.dystolic = dystolic;
    }

    public double getSystolic() {
        return systolic;
    }

    public void setSystolic(double systolic) {
        this.systolic = systolic;
    }

    public double getPulse() {
        return pulse;
    }

    public void setPulse(double pulse) {
        this.pulse = pulse;
    }

    @Override
    public String toString() {
        return "PatientBPComputeItem{" +
                "MRN='" + MRN + '\'' +
                ", loginID='" + loginID + '\'' +
                ", readingDateTime=" + readingDateTime +
                ", age=" + age +
                ", weekNumber=" + weekNumber +
                ", timeSlot=" + timeSlot +
                ", dystolic=" + dystolic +
                ", systolic=" + systolic+
                ", pulse=" + pulse +
                '}';
    }
}
