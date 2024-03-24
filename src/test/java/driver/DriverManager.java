package driver;

import common.Utils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class DriverManager {
    private static AppiumDriver driver;
    String appiumUrl = "http://127.0.0.1:4723";
    
    public AppiumDriver getDriver(Device device) {
        return switch (device) {
            case ANDROID -> getAndroidDriver();
            case IOS -> getIOSDriver();
        };
    }
    
    private String getAppPath(Device device) {
        File userDir = new File(System.getProperty("user.dir"));
        File appDir = new File(userDir, "app");
        return switch (device) {
            case ANDROID -> new File(appDir, Utils.getProperty("APP_NAME")).getAbsolutePath();
            case IOS -> "";
        };
    }
    
    private AppiumDriver getAndroidDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:platformVersion", "14");
        desiredCapabilities.setCapability("appium:deviceName", "Pixel 7 API 34");
        desiredCapabilities.setCapability("appium:app", getAppPath(Device.ANDROID));
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appium:appPackage", "com.fivemobile.thescore");
        desiredCapabilities.setCapability("appium:appActivity", ".ui.MainActivity");
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
