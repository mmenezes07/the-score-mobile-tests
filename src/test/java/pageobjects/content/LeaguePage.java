package pageobjects.content;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import pageobjects.BasePage;

public class LeaguePage extends BasePage {
    private final By title = By.id("titleTextView");
    public LeaguePage(AppiumDriver driver) {
        super(driver);
    }
    
    public String getTitle(String expectedText) {
        return getText(title, expectedText);
    }
}
