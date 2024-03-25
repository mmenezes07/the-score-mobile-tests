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

    @DataProvider(name = "teamStats")
    public static Object[][] teams() {
        return new Object[][] {
                { "Toronto Maple Leafs", new Stat[] {
                        new Stat("Goals", "3.61", "(3rd)"),
                        new Stat("Shots on Goal", "32.6", "(8th)")
                        // more stats can be added here
                        }
                },
                { "Toronto Raptors", new Stat[] { 
                        new Stat("PPG", "113.1", "(19th)"),
                        new Stat("Pace", "98.7", "(13th)")
                        // more stats can be added here
                        }
                },
        };
    }

    /**
     * 1. Click on the favorites tab
     * 2. Click on the search bar to open the search page
     * 3. Enter the team in the search bar and click on the team
     * 4. Assert that the team page opened correctly
     * 5. Click on the Team Stats sub tab and assert that it opened
     * 5. Assert that the stats defined in stats are displayed on the page
     * 6. Click go back and assert that the Search page is displayed
     * @param team the name of the team to search for
     * @param stats the list of stats to assert in the Team Stats page
     */
    @Test(dataProvider = "teamStats")
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
        String actualTeamName = teamPage.getTeamName();
        Assert.assertEquals(actualTeamName, team, STR."Expected: \{team} Actual: \{actualTeamName}");

        // Click on the team stats sub tab and assert that it opened
        TeamStatsPage teamStatsPage = teamPage.clickTeamStatsTab();
        Assert.assertTrue(teamStatsPage.isAt(), "Not on Team Stats page");
        
        /* Similar to the issue with League Selection, the actual stat values cannot be tested in this environment
        since they are always changing. The test would fail as these stat change.
        With a staging or test environment, we can specify the data and ensure the values are displayed correctly 
        Alternatively, with the API we could get the current values and assert they are being displayed correctly
        
        // Sample assertion
        Assert that the list of stats provided match
        Assert.assertTrue(teamStatsPage.areStatsDisplayed(stats));
         */
        
        // Go back and assert that screen goes back to Search page
        teamStatsPage.clickGoBack();
        Assert.assertTrue(searchResultsPage.isSearchBarDisplayed(), "Not on Search page");
        
        searchResultsPage.clickGoBack();
    }
}
