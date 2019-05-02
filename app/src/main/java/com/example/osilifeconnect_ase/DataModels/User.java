package com.example.osilifeconnect_ase.DataModels;

/**
 * Represents the user of this application. This class follows the Singleton Pattern.
 */
public class User {
    private String mrn;
    private String loginID;
    private String loginPW;
    private static User user = null;

    private User(){
        mrn = "";
        loginID = "";
        loginPW = "";
    }

    public static User getUser(){
        if(user == null) {
            user = new User();
        }
        return user;
    }

    public String getMrn() {
        return mrn;
    }

    public void setMrn(String mrn) {
        this.mrn = mrn;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getLoginPW() {
        return loginPW;
    }

    public void setLoginPW(String loginPW) {
        this.loginPW = loginPW;
    }

    @Override
    public String toString() {
        return "User{" +
                "mrn='" + mrn + '\'' +
                ", loginID='" + loginID + '\'' +
                ", loginPW='" + loginPW + '\'' +
                '}';
    }
}
