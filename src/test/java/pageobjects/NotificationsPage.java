package pageobjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NotificationsPage extends BasePage {
    private final By doneButton = By.xpath("//android.widget.TextView[contains(@resource-id,\"action_button_text\") and @text=\"Done\"]");

    public NotificationsPage(AppiumDriver driver) {
        super(driver);
    }

    public void clickDone() {
        click(doneButton);
    }
}
