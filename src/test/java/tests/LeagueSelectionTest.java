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
    
    @Test(dataProvider = "leagues")
    public void selectLeagueTest(String leagueName, String[] teamNames) {
        ContentBasePage contentPage = new ContentBasePage(driver);        
        contentPage.clickLeaguesTab();
        
        // Click on the League Selection tab
        LeagueSelectionPage leagueSelectionPage = new LeagueSelectionPage(driver);
        Assert.assertTrue(leagueSelectionPage.isAt());

        // workaround to dismiss the quick tip modal
        leagueSelectionPage.clickEdit();
        leagueSelectionPage.clickDone();
        
        // Open a league and assert that the correct league opens
        leagueSelectionPage.clickLeague(leagueName);
        LeaguePage leaguePage = new LeaguePage(driver);
        Assert.assertEquals(leaguePage.getTitle(), leagueName);

        // Click on the Standing sub tab of the league
        LeagueStandingsPage leagueStandingsPage = leaguePage.clickStandingsTab();
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
        Assert.assertTrue(leagueSelectionPage.isAt());
    }
}
