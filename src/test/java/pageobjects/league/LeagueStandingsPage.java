package pageobjects.league;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

/**
 * Standings subpage in a league page
 */
public class LeagueStandingsPage extends LeaguePage {

    public LeagueStandingsPage(AppiumDriver driver) {
        super(driver);
    }
    
    public boolean isAt() {
        return isElementSelected(standingsTab);
    }
    
    public boolean isTeamPresent(String expectedTeam) {
        return isElementDisplayed(By.xpath(STR."//android.widget.TextView[@text=\"\{expectedTeam}\"]"));
    }

    /**
     * Returns true if all teams in expectedTeams are displayed
     * @param expectedTeams the list of teams expected to be displayed
     * @return true if all teams in the expectedTeams array are displayed
     */
    public boolean areTeamsPresent(String[] expectedTeams) {
        for (String expectedTeam : expectedTeams) {
            if (!isTeamPresent(expectedTeam)) return false;
        }

        return true;
    }
}
