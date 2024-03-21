package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pageobjects.BetAlertModal;
import pageobjects.ChooseFavoritesPage;
import pageobjects.NotificationsPage;
import pageobjects.WelcomePage;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class BaseTest {

    AppiumDriver driver;

    @BeforeTest
    public void SetUp() throws MalformedURLException {
        // todo move this into driver handler class
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:platformVersion", "14");
        desiredCapabilities.setCapability("appium:deviceName", "Pixel 7 API 34");
        desiredCapabilities.setCapability("appium:app", STR."\{System.getProperty("user.dir")}/app/theScore_ Sports News & Scores_24.4.0_Apkpure.apk");
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:autoGrantPermissions", "true");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), desiredCapabilities);
        
        // todo move this into common actions?
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.clickGetStarted();

        ChooseFavoritesPage chooseFavoritesPage = new ChooseFavoritesPage(driver);
        chooseFavoritesPage.clickContinue();
        chooseFavoritesPage.selectFirstOption();
        chooseFavoritesPage.clickContinue();

        NotificationsPage notificationsPage = new NotificationsPage(driver);
        notificationsPage.clickDone();

        BetAlertModal betAlertModal = new BetAlertModal(driver);
        betAlertModal.clickClose();
    }

    @AfterTest
    public void TearDown() {
        if (driver != null) {
            driver.quit();
        }

    }
}
