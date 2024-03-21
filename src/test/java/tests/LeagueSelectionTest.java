package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.content.FavoritesPage;
import pageobjects.content.LeaguePage;
import pageobjects.content.LeagueSelectionPage;

public class LeagueSelectionTest extends BaseTest {
    @Test
    public void selectLeagueTest() {
        FavoritesPage favoritesPage = new FavoritesPage(driver);
        Assert.assertTrue(favoritesPage.isAt()); 
        
        favoritesPage.clickLeaguesTab();

        LeagueSelectionPage leagueSelectionPage = new LeagueSelectionPage(driver);
        Assert.assertTrue(leagueSelectionPage.isAt());
        
        // workaround to dismiss the quick tip modal
        leagueSelectionPage.clickEdit();
        leagueSelectionPage.clickDone();
        
        String leagueName = "NHL";
        leagueSelectionPage.clickLeague(leagueName);

        LeaguePage leaguePage = new LeaguePage(driver);
        Assert.assertEquals(leaguePage.getTitle(leagueName), leagueName);
        
        leaguePage.clickStandingsTab();
        Assert.assertTrue(leaguePage.isTeamPresent("BOS Bruins"));
        /*
        NOTE: The assertion above is not very strong/good. The teams in the playoff picture could change, causing
        the test to fail. More teams could also be asserted but the same problem exists.
        Ideally, we would have a staging or test environment where we could specify the test data for a league 
        including the team names and standings.
        We could then use that test data to assert the playoff picture accurately since we know the right standings.
        Alternatively, if we had access to the API used by this page, we could get the API data and assert that 
        the teams that are supposed to be in the playoffs get displayed on this screen and in the right spot.
         */
        
        leaguePage.clickBack();
        Assert.assertTrue(leagueSelectionPage.isAt());
    }
}
