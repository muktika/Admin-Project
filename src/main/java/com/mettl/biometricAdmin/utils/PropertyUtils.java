package com.companyname.biometricAdmin.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by shanky on 4/3/15.
 */
public class PropertyUtils {
    private static final String resourcePath = "/resources";

    private static final String baseUrlPropertyName = "baseUrl";
    private static final String cscURlPropertyName = "cscUrl";
    private static final String versionPropertyName = "biometric_version";

    private static Properties properties;
    private static Properties companynameOverrideProperties;

    private static String baseUrl = null;
    private static String cscUrl = null;
    private static String version = null;
    static {
        loadProperties();
    }

    private static String extractPropertyValue(String propertyName) {
        String propertyValue = properties.getProperty(propertyName);

        if (companynameOverrideProperties.containsKey(propertyName)) {
            propertyValue = companynameOverrideProperties.getProperty(propertyName);
        }
        if (propertyValue == null)
            throw new RuntimeException("Could not find property '" + propertyName + "' in biometric.properties or biometric_url.properties");

        return propertyValue;
    }

    public static String getBaseUrl() {
        if (baseUrl == null) {
            baseUrl=extractPropertyValue(baseUrlPropertyName);
        }
        return baseUrl;
    }

    public static String getCscUrl() {
        if (cscUrl == null) {
            cscUrl=extractPropertyValue(cscURlPropertyName);
        }
        return cscUrl;
    }

    public static String Version() {
        if (version == null) {
            version=extractPropertyValue(versionPropertyName);
        }
        return version;
    }

    public static String js(String path) {
        return String.format(resourcePath+"/js/%s.js?v=%s", path, Version());
    }

    public static String css(String path) {
        return String.format(resourcePath+"/css/%s.css?v=%s", path, Version());
    }

    public static String IMAGE(String path) {
        return String.format(resourcePath+"/img/%s?v=%s", path, Version());
    }

    private static void loadProperties() {
        try {
            if (properties == null)
                properties = new Properties();

            properties.load(PropertyUtils.class.getResourceAsStream("/biometric.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Could not read resource file biometric.properties");
        }

        String overrideFile = null;
        try {
            overrideFile = System.getProperty("user.home") + File.separator + "companynameconfig" + File.separator + "biometric_url.properties";
            File file = new File(overrideFile);
            if (file.exists()){
                companynameOverrideProperties = new Properties();
                companynameOverrideProperties.load(new FileInputStream(file));
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not read resource file from file system - " + overrideFile);
        }
    }
}
