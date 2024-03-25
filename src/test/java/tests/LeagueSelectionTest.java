package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.content.ContentBasePage;
import pageobjects.league.LeaguePage;
import pageobjects.content.LeagueSelectionPage;
import pageobjects.league.LeagueStandingsPage;

public class LeagueSelectionTest extends BaseTest {
    
    @DataProvider(name = "leagues")
    public static Object[][] leagues() {
        return new Object[][] {
                { "NHL", new String[] { 
                        "BOS Bruins", "FLA Panthers" }
                }, 
                { "NBA", new String[] { 
                        "BOS Celtics", "MIL Bucks" }
                }
        };
    }

    /**
     * 1. Click on the leagues tab
     * 2. Click on the league with leagueName
     * 3. Assert that the correct league opened
     * 4. Click on the Standings sub tab of the league
     * 5. Assert that all teams from teamNames display on the page
     * 6. Click go back and assert that the League page is displayed
     * @param leagueName the name of the league to open
     * @param teamNames the list of teams in the league
     */
    @Test(dataProvider = "leagues")
    public void selectLeagueTest(String leagueName, String[] teamNames) {
        ContentBasePage contentPage = new ContentBasePage(driver);
        // Click on the League Selection tab
        contentPage.clickLeaguesTab();
        LeagueSelectionPage leagueSelectionPage = new LeagueSelectionPage(driver);
        Assert.assertTrue(leagueSelectionPage.isAt(), "Not on League Selection page");

        // workaround to dismiss the quick tip modal
        leagueSelectionPage.clickEdit();
        leagueSelectionPage.clickDone();
        
        // Open a league and assert that the correct league opens
        leagueSelectionPage.clickLeague(leagueName);
        LeaguePage leaguePage = new LeaguePage(driver);
        String actualTitle = leaguePage.getTitle();
        Assert.assertEquals(actualTitle, leagueName, STR."Expected: \{leagueName} Actual: \{actualTitle}");

        // Click on the Standings sub tab of the league
        // Assert the correct tab is opened
        LeagueStandingsPage leagueStandingsPage = leaguePage.clickStandingsTab();
        Assert.assertTrue(leagueStandingsPage.isAt(), "Not on League Standings Page");
        
        Assert.assertTrue(leagueStandingsPage.areTeamsPresent(teamNames));
        /*
        NOTE: The assertion above is not very strong/good. The teams in the playoff picture could change, causing
        the test to fail. More teams could also be asserted but the same problem exists.
        Ideally, we would have a staging or test environment where we could specify the test data for a league 
        including the team names and standings.
        We could then use that test data to assert the playoff picture accurately since we know the right standings.
        Alternatively, if we had access to the API used by this page, we could get the API data and assert that 
        the teams that are supposed to be in the playoffs get displayed on this screen and in the right spot.
         */

        // Go back and assert the app navigates to the League Selection page
        leaguePage.clickBack();
        Assert.assertTrue(leagueSelectionPage.isAt(), "Not on League Selection Page");
    }
}
