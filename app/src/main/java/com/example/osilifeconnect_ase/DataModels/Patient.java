package com.example.osilifeconnect_ase.DataModels;

import java.sql.Date;

/**
 * Represents a patient.
 */
public class Patient {
    private static String[] validStates = { "alabama", "alaska", "arizona", "arkansas", "california", "colorado", "connecticut", "delaware",
            "florida", "georgia", "hawaii", "idaho", "illinois", "indiana", "iowa", "kansas", "kentucky", "louisiana", "maine", "maryland", "massachusetts",
            "michigan", "minnesota", "mississippi", "missouri", "montana", "nebraska", "nevada", "new hampshire", "new jersey", "new mexico", "new york",
            "north carolina", "north dakota", "ohio", "oklahoma", "oregon", "pennsylvania", "rhode island", "south carolina", "south dakota",
            "tennessee", "texas", "utah", "vermont", "virginia", "washington", "west virginia", "wisconsin", "wyoming" };
    private static String[] validGenders = { "male", "female", "identifies male", "identifies female", "na (gender)"};
    private static String[] validEthnicities = {"hispanic/latino", "not hispanic/latino", "na (ethnicity)"};
    private static String[] validRaces = {"american indian/alaska native", "asian", "black/african", "hispanic/latino",
            "native hawaiian/pacific islander", "white/caucasian", "na (race)"};
    private static String[] validMaritalStatus = {"married", "married (w/ family)", "single (living alone)", "single (w/ family)", "widow (living alone)",
            "widow (w/ family)", "na (marital)"};
    private static String[] validEmployment = {"not employed", "part-time (hourly)", "full-time (hourly)","full-time (salaried)", "self-employed",
            "disability", "retired", "na (employed)"};
    private static String[] validSmokingStatus = {"smokes >10 yrs", "smokes 8-10 yrs", "smokes 5-7 yrs", "smokes 3-4 yrs", "smokes 1-2 yrs", "smokes <1 yr",
            "quit smoking >10 yrs ago", "quit smoking 8-10 yrs ago", "quit smoking 5-7 yrs ago", "quit smoking 3-4 yrs ago", "quit smoking 1-2 yrs ago",
            "quit smoking <1 yr ago", "never smoked", "na (smoking)"};
    private static String[] validDiagnosis = {"asthma", "chf", "copd", "diabetes", "hypertension", "ihd", "osteoarthritis"};
    private static String[] validMobility = {"not limited", "limited - minor - impacts athletics", "limited - moderate - impacts norm",
            "limited - major - impacts norm", "restricted - physical inability/no aids", "restricted - physical inability/uses aids",
            "very restricted - bed ridden", "na (mobility)"};
    private static String[] validLanguages = {"english", "spanish", "chinese", "french/french creole", "tagalog", "vietnamese", "na (language)"};
    private static String sNull = "NULL";
    private String MRN;
    private String loginID;
    private String loginPW;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String firstName;
    private String lastName;
    private String gender;
    private String ethnicity;
    private String race;
    private Date dateOfBirth; //should be Date and Time? or Date?
    private String maritalStatus;
    private String employment;
    private int heightInches;
    private String smokingStatus;
    private String diagnosisOne;
    private String diagnosisTwo;
    private String mobility;
    private String language;

    public Patient(String MRN, String loginID, String loginPW, String address, String city, String state, String zip,
                   String firstName, String lastName, String gender, String ethnicity, String race, Date dateOfBirth,
                   String maritalStatus, String employment, int heightInches, String smokingStatus, String diagnosisOne,
                   String diagnosisTwo, String mobility, String language) {
        this.MRN = MRN;
        this.loginID = loginID;
        this.loginPW = loginPW;
        this.address = address;
        this.city = city;
        this.setState(state);
        this.zip = zip;
        this.firstName = firstName;
        this.lastName = lastName;
        this.setGender(gender);
        this.setEthnicity(ethnicity);
        this.setRace(race);
        this.dateOfBirth = dateOfBirth;
        this.setMaritalStatus(maritalStatus);
        this.setEmployment(employment);
        this.heightInches = heightInches;
        this.setSmokingStatus(smokingStatus);
        this.setDiagnosisOne(diagnosisOne);
        this.setDiagnosisTwo(diagnosisTwo);
        this.setMobility(mobility);
        this.setLanguage(language);
    }

    /**
     * Returns a boolean value specifiying if a string array contains a specific string.
     * @param arr The array to be parsed
     * @param str The string to be found
     * @return A boolean value specifying if this string array contains a specific string
     */
    private boolean contains(String[] arr, String str){
        boolean isValid = false;
        for(int i = 0; i < arr.length; i++){
            if(arr[i].equalsIgnoreCase(str)){
                isValid = true;
                break;
            }
        }
        return isValid;
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

    public String getLoginPW() {
        return loginPW;
    }

    public void setLoginPW(String loginPW) {
        this.loginPW = loginPW;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if(this.contains(validStates, state)){
            this.state = state.toLowerCase();
        }else {
            this.state = sNull;
        }
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if(this.contains(validGenders, gender)){
            this.gender = gender.toLowerCase();
        }else{
            this.gender = sNull;
        }
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        if(this.contains(validEthnicities, ethnicity)){
            this.ethnicity = ethnicity.toLowerCase();
        }else{
            this.ethnicity = sNull;
        }
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        if(this.contains(validRaces, race)){
            this.race = race.toLowerCase();
        }else{
            this.race = sNull;
        }
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        if(this.contains(validMaritalStatus, maritalStatus)){
            this.maritalStatus = maritalStatus.toLowerCase();
        }else{
            this.maritalStatus = sNull;
        }
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        if(this.contains(validEmployment, employment)){
            this.employment = employment.toLowerCase();
        }else{
            this.employment = sNull;
        }
    }

    public int getHeightInches() {
        return heightInches;
    }

    public void setHeightInches(int heightInches) {
        this.heightInches = heightInches;
    }

    public String getSmokingStatus() {
        return smokingStatus;
    }

    public void setSmokingStatus(String smokingStatus) {
        if(this.contains(validSmokingStatus, smokingStatus)){
            this.smokingStatus = smokingStatus.toLowerCase();
        }else{
            this.smokingStatus = sNull;
        }
    }

    public String getDiagnosisOne() {
        return diagnosisOne;
    }

    public void setDiagnosisOne(String diagnosisOne) {
        if(this.contains(validDiagnosis, diagnosisOne)){
            this.diagnosisOne = diagnosisOne.toLowerCase();
        }else{
            this.diagnosisOne = sNull;
        }
    }

    public String getDiagnosisTwo() {
        return diagnosisTwo;
    }

    public void setDiagnosisTwo(String diagnosisTwo) {
        if(this.contains(validDiagnosis, diagnosisTwo)){
            this.diagnosisTwo = diagnosisTwo.toLowerCase();
        }else{
            this.diagnosisTwo = sNull;
        }
    }

    public String getMobility() {
        return mobility;
    }

    public void setMobility(String mobility) {
        if(this.contains(validMobility, mobility)){
            this.mobility = mobility.toLowerCase();
        }else{
            this.mobility = sNull;
        }
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        if(this.contains(validLanguages, language)){
            this.language = language.toLowerCase();
        }else{
            this.language = sNull;
        }
    }

    @Override
    public String toString() {
        return "Patient{" +
                "MRN='" + MRN + '\'' +
                ", loginID='" + loginID + '\'' +
                ", loginPW='" + loginPW + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", ethnicity='" + ethnicity + '\'' +
                ", race='" + race + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", employment='" + employment + '\'' +
                ", heightInches=" + heightInches +
                ", smokingStatus='" + smokingStatus + '\'' +
                ", diagnosisOne='" + diagnosisOne + '\'' +
                ", diagnosisTwo='" + diagnosisTwo + '\'' +
                ", mobility='" + mobility + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
