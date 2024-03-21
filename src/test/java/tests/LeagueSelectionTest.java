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
        
        String league = "NHL";
        leagueSelectionPage.clickLeague(league);

        LeaguePage leaguePage = new LeaguePage(driver);
        Assert.assertEquals(leaguePage.getTitle(league), league);
    }
}
