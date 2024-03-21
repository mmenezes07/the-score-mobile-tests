package pageobjects.team;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import pageobjects.BasePage;

public class TeamPage extends BasePage {
    private final By teamName = By.id("team_name");
    private final By teamStatsTab = By.xpath("//android.widget.LinearLayout[@content-desc=\"Team Stats\"]");
    private final By goBackButton = By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]");
    
    public TeamPage(AppiumDriver driver) {
        super(driver);
    }
    
    public String getTeamName(String expectedText) {
        return getText(teamName, expectedText);
    }
    
    public TeamStatsPage clickTeamStatsTab() {
        click(teamStatsTab);
        return new TeamStatsPage(driver);
    }
    
    public boolean isTeamStatsTabSelected() {
        return isElementSelected(teamStatsTab);
    }
    
    public void clickGoBack() {
        click(goBackButton);
    }
}
