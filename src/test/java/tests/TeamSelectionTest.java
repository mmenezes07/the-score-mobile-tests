package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.SearchResultsPage;
import pageobjects.content.ContentBasePage;
import pageobjects.content.FavoritesPage;
import pageobjects.team.TeamPage;
import pageobjects.team.TeamStatsPage;

public class TeamSelectionTest extends BaseTest {
    @Test
    public void searchTeamTest() {
        ContentBasePage contentPage = new ContentBasePage(driver);
        contentPage.clickFavoritesTab();
        
        String team = "Toronto Maple Leafs";
        FavoritesPage favoritesPage = new FavoritesPage(driver);
        favoritesPage.clickSearchBar();

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.inputSearchBar(team);
        searchResultsPage.clickSearchResultByText(team);

        TeamPage teamPage = new TeamPage(driver);
        Assert.assertEquals(teamPage.getTeamName(), team);

        TeamStatsPage teamStatsPage = teamPage.clickTeamStatsTab();
        Assert.assertTrue(teamStatsPage.isTeamStatsTabSelected());

        Assert.assertTrue(teamStatsPage.isStatDisplayed("Goals"));
        Assert.assertFalse(teamStatsPage.isStatDisplayed("3 point %"));
        // More stats can be asserted here

        /* Similar to the issue with League Selection, the actual stat values cannot be tested in this environment
        since they are always changing.
        With a staging or test environment, we can specify the data and ensure the values are displayed correctly 
        Alternatively, with the API we could get the current values and assert they are being displayed correctly

        Sample Assertions:
        Assert.assertEquals(teamStatsPage.getStatValue("Shots on Goal"), "32.6");
        Assert.assertEquals(teamStatsPage.getStatRank("Goals Allowed"), "(19th)");
         */
        
        // assert that the values and rank are not null and not empty instead
        String shotsOnGoalValue = teamStatsPage.getStatValue("Shots on Goal");
        String goalsAllowedRank = teamStatsPage.getStatRank("Goals Allowed");
        Assert.assertNotNull(shotsOnGoalValue);
        Assert.assertFalse(shotsOnGoalValue.isEmpty());
        Assert.assertNotNull(goalsAllowedRank);
        Assert.assertFalse(goalsAllowedRank.isEmpty());
        
        teamStatsPage.clickGoBack();
        Assert.assertTrue(favoritesPage.isAt());
    }
}
