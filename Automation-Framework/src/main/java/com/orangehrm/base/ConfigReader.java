package com.orangehrm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties pro;

    // Static block for initializing the Properties object
    static {
        try {
            pro = new Properties();
            String projectDir = System.getProperty("user.dir");
            FileInputStream fis = new FileInputStream(projectDir + "/src/main/resources/config.properties");
            pro.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    // Method to get property value by key
    public static String getProperty(String key) {
        return pro.getProperty(key);
    }
}