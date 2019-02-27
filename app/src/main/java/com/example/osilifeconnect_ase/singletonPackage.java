package com.example.osilifeconnect_ase;

public class singletonPackage {

    private static singletonPackage instance = null;

    public static singletonPackage getInstance() {
        if(instance == null)
            instance = new singletonPackage();
        return instance;
    }

    public void switchActivity(String title){
        switch(title) {
            case "Weight":
                //TODO: Intent to weight activity
                break;
            case "Devices":
                //TODO: Intent to devices activity
                break;
            case "Contact Us":
                //TODO: Intent to contact us activity
                break;
            case "Settings":
                //TODO: Intent to settings activity
                break;
        }
    }

}
