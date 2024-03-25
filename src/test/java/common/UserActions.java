package common;

import io.appium.java_client.AppiumDriver;
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
    public static void NavigateToHomePage(AppiumDriver driver) {
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
}
