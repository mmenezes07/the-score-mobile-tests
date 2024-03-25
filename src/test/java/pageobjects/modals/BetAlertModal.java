package pageobjects.modals;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import pageobjects.BasePage;

public class BetAlertModal extends BasePage {
    private final By closeButton = By.id("dismiss_modal");
    
    public BetAlertModal(AppiumDriver driver) {
        super(driver);
    }
    
    public void clickClose() {
        click(closeButton);
    }
}
