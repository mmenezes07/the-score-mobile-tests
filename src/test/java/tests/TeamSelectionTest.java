package tests;

import common.Stat;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.SearchResultsPage;
import pageobjects.content.ContentBasePage;
import pageobjects.team.TeamPage;
import pageobjects.team.TeamStatsPage;

public class TeamSelectionTest extends BaseTest {

    @DataProvider(name = "teams")
    public static Object[][] teams() {
        return new Object[][] {
                { "Toronto Maple Leafs", new Stat[] {
                        new Stat("Goals", "3.65", "(2nd)"),
                        new Stat("Shots on Goal", "32.6", "(8th)")}
                },
                { "Toronto Raptors", new Stat[] { 
                        new Stat("PPG", "113.1", "(T18th)"),
                        new Stat("Pace", "98.7", "(13th)")}
                },
        };
    }
    
    @Test(dataProvider = "teams")
    public void searchTeamAndStatsTest(String team, Stat[] stats) {
        ContentBasePage contentPage = new ContentBasePage(driver);
        contentPage.clickFavoritesTab();
        contentPage.clickSearchBar();

        // Search for a team and click on the result
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.inputSearchBar(team);
        searchResultsPage.clickSearchResultByText(team);

        // Assert that the team page opens correctly
        TeamPage teamPage = new TeamPage(driver);
        Assert.assertEquals(teamPage.getTeamName(), team);

        // Click on the team stats sub tab and assert that it opened
        TeamStatsPage teamStatsPage = teamPage.clickTeamStatsTab();
        Assert.assertTrue(teamStatsPage.isAt());
        
        // Assert that the list of stats provided match
        Assert.assertTrue(teamStatsPage.areStatsDisplayed(stats));

        /* Similar to the issue with League Selection, the actual stat values cannot be tested in this environment
        since they are always changing.
        With a staging or test environment, we can specify the data and ensure the values are displayed correctly 
        Alternatively, with the API we could get the current values and assert they are being displayed correctly
         */
        
        // Go back and assert that screen goes back to Search page
        teamStatsPage.clickGoBack();
        Assert.assertTrue(searchResultsPage.isSearchBarDisplayed());
        
        searchResultsPage.clickGoBack();
    }
}
