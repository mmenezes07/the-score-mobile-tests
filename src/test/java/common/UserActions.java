package common;

import io.appium.java_client.AppiumDriver;
import pageobjects.BetAlertModal;
import pageobjects.ChooseFavoritesPage;
import pageobjects.NotificationsPage;
import pageobjects.WelcomePage;

public class UserActions {
    public static void NavigateToHomePage(AppiumDriver driver) {
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
}
