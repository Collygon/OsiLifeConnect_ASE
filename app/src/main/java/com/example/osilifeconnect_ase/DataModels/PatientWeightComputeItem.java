package com.example.osilifeconnect_ase.DataModels;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PatientWeightComputeItem {
    private String MRN;
    private String loginID;
    private Timestamp readingDateTime;
    private int age; //=(today-DOB)/365
    private int bodyMassIndex; //"ROUNDUP((703*Val1)/(Height*Height),1)"
    private int weekNumber; //"=WEEKNUM(ReadingDate,1)"
    private String timeSlot; //=IF(ReadingDateTime)=(0100-0900, AM-Initial)
    private double weight; //val1

    public PatientWeightComputeItem(String MRN, String loginID, Timestamp readingDateTime, int age,
                                    int bodyMassIndex, int weekNumber, String timeSlot, double weight) {
        this.MRN = MRN;
        this.loginID = loginID;
        this.readingDateTime = readingDateTime;
        this.age = age;
        this.bodyMassIndex = bodyMassIndex;
        this.weekNumber = weekNumber;
        this.timeSlot = timeSlot;
        this.weight = weight;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBodyMassIndex() {
        return bodyMassIndex;
    }

    public void setBodyMassIndex(int bodyMassIndex) {
        this.bodyMassIndex = bodyMassIndex;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "PatientWeightComputeItem{" +
                "MRN='" + MRN + '\'' +
                ", loginID='" + loginID + '\'' +
                ", readingDateTime=" + readingDateTime +
                ", age=" + age +
                ", bodyMassIndex=" + bodyMassIndex +
                ", weekNumber=" + weekNumber +
                ", timeSlot=" + timeSlot +
                ", weight=" + weight +
                '}';
    }
}
