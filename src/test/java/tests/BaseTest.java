package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class BaseTest {

    AppiumDriver driver;

    @BeforeTest
    public void SetUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:platformVersion", "14");
        desiredCapabilities.setCapability("appium:deviceName", "Pixel 7 API 34");
        desiredCapabilities.setCapability("appium:app", STR."\{System.getProperty("user.dir")}/app/theScore_ Sports News & Scores_24.4.0_Apkpure.apk");
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), desiredCapabilities);
    }

    @AfterTest
    public void TearDown() {
        if (driver != null) {
            driver.quit();
        }

    }
}
