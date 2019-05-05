package com.example.osilifeconnect_ase;

import java.util.HashMap;

/**
 * This class includes a small subset of standard GATT attributes for demonstration purposes.
 */
public class GattAttributes {
    private static HashMap<String, String> attributes = new HashMap<>();
    public static String BLOOD_PRESSURE_MEASUREMENT = "00002A35-0000-1000-8000-00805F9B34FB";
    public static String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805F9B34FB";

    static {
        // Services.
        attributes.put("00001810-0000-1000-8000-00805F9B34FB", "Blood Pressure Measurement");
        attributes.put("0000180A-0000-1000-8000-00805F9B34FB", "Device Information Service");
        attributes.put("0000181D-0000-1000-8000-00805F9B34FB", "Weight Scale Service");
        // Characteristics.
        attributes.put(BLOOD_PRESSURE_MEASUREMENT, "Blood Pressure Measurement");
        attributes.put("Indie Health", "Manufacturer Name String");
        attributes.put("00002A49-0000-1000-8000-00805F9B34FB", "Blood Pressure Feature");
    }

    public static String lookup(String uuid, String defaultName) {
        String name = attributes.get(uuid);
        return name == null ? defaultName : name;
    }
}
