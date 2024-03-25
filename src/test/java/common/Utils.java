package common;

import java.io.FileInputStream;
import java.util.Properties;

public class Utils {
    private static final String configFilePath = "/src/test/java/common/config.properties";

    /**
     * Returns the properties found in the config.properties file
     * @return the properties in the config.properties file
     */
    private static Properties readProperties() {
        Properties properties = new Properties();
        String filePath = System.getProperty("user.dir") + configFilePath;
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            properties.load(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return properties;
    }

    /**
     * Returns the value of a property by name
     * @param propertyName the name of the property to get
     * @return the values of the property
     */
    public static String getProperty(String propertyName) {
        return readProperties().getProperty(propertyName);
    }
}
