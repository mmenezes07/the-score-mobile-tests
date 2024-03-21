package pageobjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePage extends BasePage {
    private final By getStartedButton = By.id("action_button_text");

    public WelcomePage(AppiumDriver driver) {
        super(driver);
    }

    public void clickGetStarted() {
        click(getStartedButton);
    }
}
