package tests;

import org.testng.annotations.Test;
import pageobjects.WelcomePage;

public class FirstTest extends BaseTest {
    @Test
    public void OpenApp() {
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.clickGetStarted();
    }
}
