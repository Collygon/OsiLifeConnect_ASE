package com.example.osilifeconnect_ase;

public class singletonPackage {

    private static singletonPackage instance = null;

    public static singletonPackage getInstance() {
        if(instance == null)
            instance = new singletonPackage();
        return instance;
    }
}
