package pageobjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ChooseFavoritesPage extends BasePage {
    private final By continueButton = By.xpath("//android.widget.TextView[contains(@resource-id,\"action_button_text\") and @text=\"Continue\"]");
    private final By firstOption = By.xpath("//androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]");
    
    public ChooseFavoritesPage(AppiumDriver driver) {
        super(driver);
    }

    public void clickContinue() {
        click(continueButton);
    }
    
    public void selectFirstOption() {
        click(firstOption);
    }
}
