package pageobjects.content;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import pageobjects.BasePage;

public class LeaguePage extends BasePage {
    private final By title = By.id("titleTextView");
    private final By standingsTab = By.xpath("//android.widget.TextView[@text=\"STANDINGS\"]");
    private final By backButton = By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]");
    
    public LeaguePage(AppiumDriver driver) {
        super(driver);
    }
    
    public String getTitle() {
        return getText(title);
    }
    
    public void clickStandingsTab() {
        click(standingsTab);
    }
    
    public boolean isTeamPresent(String expectedTeam) {
        return isElementDisplayed(By.xpath(STR."//android.widget.TextView[@text=\"\{expectedTeam}\"]"));
    }
    
    public boolean areTeamsPresent(String[] expectedTeams) {
        for (String expectedTeam : expectedTeams) {
            if (!isTeamPresent(expectedTeam)) return false;
        }
        
        return true;
    }
    
    public void clickBack() {
        click(backButton);
    }
}
