package tests;

import common.UserActions;
import common.Utils;
import driver.Device;
import driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    AppiumDriver driver;

    @BeforeClass
    public void setUp(){
        driver = new DriverManager().getDriver(Device.ANDROID);
        UserActions.navigateToHomePage(driver);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @BeforeMethod
    public void launchApp() {
        UserActions.startActivity(driver, Utils.getProperty("APP_PACKAGE"), Utils.getProperty("APP_ACTIVITY"));
    }

    /**
     * Terminates the app after each method (for param tests)
     * This ensures that if a test fails in a random screen, the app still resets so the next test is not affected
     */
    @AfterMethod
    public void resetApp() {
        UserActions.terminateApp(driver, Utils.getProperty("APP_PACKAGE"));
    }
}
