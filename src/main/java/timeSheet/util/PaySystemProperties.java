package timeSheet.util;

import timeSheet.util.properties.EncryptedProperties;

import java.io.File;
import java.io.IOException;

/**
 * User: John Lawrence
 * Date: 12/25/10
 * Time: 11:59 PM
 */
public class PaySystemProperties {
    private static EncryptedProperties properties;

    private static String fileName = "paySystem.properties";
    private static String dirPath = System.getProperty("user.home") + File.separator + ".PaySystem";

    private PaySystemProperties() {
    }

    public static String getProperty(PropertyName property) {
        checkProperties();
        return properties.getProperty(property.getName());
    }

    public static String getProperty(PropertyName property, String defaultValue) {
        checkProperties();
        return properties.getProperty(property.getName(), defaultValue);
    }

    public static void setProperty(PropertyName name, String property) {
        checkProperties();
        properties.setProperty(name.getName(), property);
        saveProperties();
    }

    public static void saveProperties() {
        File propertiesFile = new File(dirPath + File.separator + fileName);
        try {
            properties.store(propertiesFile, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkProperties() {
        if (properties == null) {
            File propertiesFile = new File(dirPath + File.separator + fileName);
            File propertiesDir = new File(dirPath);

            if (!propertiesDir.exists()) {
                propertiesDir.mkdir();
            }
            if (!propertiesFile.exists()) {
                try {
                    propertiesFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            properties = new EncryptedProperties();
            try {
                properties.load(propertiesFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
