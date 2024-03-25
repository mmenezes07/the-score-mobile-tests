package common;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.appmanagement.ApplicationState;
import pageobjects.modals.BetAlertModal;
import pageobjects.ChooseFavoritesPage;
import pageobjects.NotificationsPage;
import pageobjects.WelcomePage;

/**
 * Methods to run common actions performed in the app
 * For example, Login or Navigating to home page
 */
public class UserActions {
    /**
     * Navigates through the welcome screen of the app when the app is first installed
     * @param driver the appium driver
     */
    public static void navigateToHomePage(AppiumDriver driver) {
        WelcomePage welcomePage = new WelcomePage(driver);
        if (!welcomePage.isAt()) {
            return;
        }
        
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

    /**
     * Terminates the app with the given appPackage
     * @param driver the Appium driver
     * @param appPackage the id of the app to terminate
     */
    public static void terminateApp(AppiumDriver driver, String appPackage) {
        System.out.println(STR."Terminating app with id: \{appPackage}");
        driver.executeScript("mobile: terminateApp", ImmutableMap.of("appId", appPackage));
    }

    /**
     * Starts the app with given appPackage/appActivity
     * @param driver the Appium driver
     * @param appPackage the appPackage to start
     * @param appActivity the appActivity to start
     */
    public static void startActivity(AppiumDriver driver, String appPackage, String appActivity) {
        String appIntent =  STR."\{appPackage}/\{appActivity}";
        System.out.println(STR."Starting activity with: \{appIntent}");
        driver.executeScript("mobile: startActivity", ImmutableMap.of("intent", appIntent));
    }
}
