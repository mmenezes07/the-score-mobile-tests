package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {
    private static final String configFilePath = "/src/test/java/common/config.properties";
    
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
    
    public static String getProperty(String propertyName) {
        return readProperties().getProperty(propertyName);
    }
}
