package pageobjects.league;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import pageobjects.BasePage;

public class LeaguePage extends BasePage {
    private final By title = By.id("titleTextView");
    protected final By standingsTab = By.xpath("//android.widget.TextView[@text=\"STANDINGS\"]");
    private final By backButton = By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]");
    
    public LeaguePage(AppiumDriver driver) {
        super(driver);
    }
    
    public String getTitle() {
        return getText(title);
    }
    
    public LeagueStandingsPage clickStandingsTab() {
        click(standingsTab);
        return new LeagueStandingsPage(driver);
    }
    
    public void clickBack() {
        click(backButton);
    }
}
