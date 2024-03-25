package driver;

import common.Utils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * This class is used to get the driver for the test
 */
public class DriverManager {
    private static AppiumDriver driver;
    String appiumUrl = "http://127.0.0.1:4723";

    /**
     * Returns an Appium Driver with capabilities depending on the device
     * @param device the type of device android or ios
     * @return An Appium Driver
     */
    public AppiumDriver getDriver(Device device) {
        return switch (device) {
            case ANDROID -> getAndroidDriver();
            case IOS -> getIOSDriver();
        };
    }

    /**
     * Returns the path to the app under test
     * @param device the type of device android or ios
     * @return the path to the app under test
     */
    private String getAppPath(Device device) {
        File userDir = new File(System.getProperty("user.dir"));
        File appDir = new File(userDir, "app");
        return switch (device) {
            case ANDROID -> new File(appDir, Utils.getProperty("APP_NAME")).getAbsolutePath();
            case IOS -> ""; // TODO add support for IOS
        };
    }

    /**
     * Sets the desired capabilities for an android device and returns the driver with the capabilities
     * @return the driver with set desired capabilities
     */
    private AppiumDriver getAndroidDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:platformVersion", "14");
        desiredCapabilities.setCapability("appium:deviceName", "Pixel 7 API 34");
        desiredCapabilities.setCapability("appium:app", getAppPath(Device.ANDROID));
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appium:appPackage", Utils.getProperty("APP_PACKAGE"));
        desiredCapabilities.setCapability("appium:appActivity", Utils.getProperty("APP_ACTIVITY"));
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:autoGrantPermissions", true);
        
        return getDriverHelper(desiredCapabilities);
    }

    private AppiumDriver getIOSDriver() {
        // TODO Implement iOS Driver capabilities
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:app", getAppPath(Device.IOS));
        return getDriverHelper(desiredCapabilities);
    }
    
    private AppiumDriver getDriverHelper(DesiredCapabilities desiredCapabilities) {
        try {
            URI uri = new URI(appiumUrl);
            driver = new AppiumDriver(uri.toURL(), desiredCapabilities);
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
        
        return driver;
    }
}
