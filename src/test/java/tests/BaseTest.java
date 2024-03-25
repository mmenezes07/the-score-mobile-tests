package tests;

import common.UserActions;
import driver.Device;
import driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;

public class BaseTest {
    AppiumDriver driver;

    @BeforeClass
    public void setUp(){
        driver = new DriverManager().getDriver(Device.ANDROID);
        UserActions.NavigateToHomePage(driver);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
