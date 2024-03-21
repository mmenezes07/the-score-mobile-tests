package pageobjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePage extends BasePage {
    private final By getStartedButton = By.xpath("//android.widget.TextView[contains(@resource-id,\"action_button_text\") and @text=\"Get Started\"]");

    public WelcomePage(AppiumDriver driver) {
        super(driver);
    }

    public void clickGetStarted() {
        click(getStartedButton);
    }
}
