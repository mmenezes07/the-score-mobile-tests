package pageobjects.content;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class LeagueSelectionPage extends ContentBasePage {
    private final By editButton = By.xpath("//android.widget.TextView[@text=\"Edit\"]");
    private final By doneButton = By.xpath("//android.widget.TextView[@text=\"Done\"]");
    
    public LeagueSelectionPage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isAt() {
        return isElementSelected(leaguesTab);
    }

    /**
     * Returns the locator for a league
     * Returns the base ViewGroup so the interior actions like pinning can be used 
     * @param leagueName the name of the league to get
     * @return the locator of the league
     */
    private By getLeague(String leagueName) {
        return By.xpath(STR."//android.view.ViewGroup[./android.widget.TextView[@text=\"\{leagueName}\"]]");
    }
    
    public void clickLeague(String leagueName) {
        click(getLeague(leagueName));
    }
    
    public void clickEdit() {
        click(editButton);
    }
    
    public void clickDone() {
        click(doneButton);
    }
}
