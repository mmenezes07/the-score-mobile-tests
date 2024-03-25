package pageobjects.team;

import common.Stat;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.util.Objects;

public class TeamStatsPage extends TeamPage {
    public TeamStatsPage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isAt() {
        return isElementSelected(teamStatsTab);
    }
    
    private String getStatByCategoryXPath(String category) {
        return STR."//android.view.ViewGroup[./android.widget.TextView[contains(@resource-id,\"text_category_name\") and @text=\"\{category}\"]]";
    }

    /**
     * Returns the locator for the entire stat row so the other information from the stat can be read
     * @param category the name of the stat to get
     * @return the locator the stat row with name category
     */
    private By getStatByCategory(String category) {
        return By.xpath(getStatByCategoryXPath(category));
    }

    /**
     * Returns true if the stat with name category is displayed
     * @param category the name of the stat
     * @return true if the stat with name category is displayed
     */
    public boolean isStatCategoryDisplayed(String category) {
        return isElementDisplayed(getStatByCategory(category));
    }

    /**
     * Returns the value of a stat with name category
     * @param category the name of the stat
     * @return the value of the stat with name category
     */
    public String getStatValue(String category) {
        String statValueXpath = "/android.widget.TextView[contains(@resource-id,\"text_value\")]";
        By statValue = By.xpath(STR."\{getStatByCategoryXPath(category)}\{statValueXpath}");
        return getText(statValue);
    }

    /**
     * Returns the rank of a stat
     * @param category the name of the stat
     * @return the rank of a stat
     */
    public String getStatRank(String category) {
        String statRankXpath = "/android.widget.TextView[contains(@resource-id,\"text_formatted_rank\")]";
        By statRank = By.xpath(STR."\{getStatByCategoryXPath(category)}\{statRankXpath}");
        return getText(statRank);
    }

    /**
     * Returns true if the stat displays the right category, value and rank
     * @param stat an object with the category, value and rank of the stat
     * @return true if the stat displays the right category, value and rank
     */
    public boolean isStatDisplayed(Stat stat) {
        return isStatCategoryDisplayed(stat.category) && 
                Objects.equals(getStatValue(stat.category), stat.value) &&
                Objects.equals(getStatRank(stat.category), stat.rank);
    }

    /**
     * Returns true if the array of stats is displayed correctly
     * @param stats an array of stats
     * @return true if the array of stats is displayed correctly
     */
    public boolean areStatsDisplayed(Stat[] stats) {
        for (Stat stat : stats) {
            if (!isStatDisplayed(stat)) return false;
        }
        
        return true;
    }
}
