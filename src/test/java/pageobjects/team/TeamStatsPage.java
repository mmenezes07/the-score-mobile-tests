package pageobjects.team;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class TeamStatsPage extends TeamPage {
    public TeamStatsPage(AppiumDriver driver) {
        super(driver);
    }
    
    private String getStatByCategoryXPath(String category) {
        return STR."//android.view.ViewGroup[./android.widget.TextView[contains(@resource-id,\"text_category_name\") and @text=\"\{category}\"]]";
    }
    private By getStatByCategory(String category) {
        return By.xpath(getStatByCategoryXPath(category));
    }
    
    public boolean isStatDisplayed(String category) {
        return isElementDisplayed(getStatByCategory(category));
    }
    
    public String getStatValue(String category) {
        String statValueXpath = "/android.widget.TextView[contains(@resource-id,\"text_value\")]";
        By statValue = By.xpath(STR."\{getStatByCategoryXPath(category)}\{statValueXpath}");
        return getText(statValue);
    }

    public String getStatRank(String category) {
        String statRankXpath = "/android.widget.TextView[contains(@resource-id,\"text_formatted_rank\")]";
        By statRank = By.xpath(STR."\{getStatByCategoryXPath(category)}\{statRankXpath}");
        return getText(statRank);
    }
}
