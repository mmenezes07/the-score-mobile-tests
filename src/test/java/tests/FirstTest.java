package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.WelcomePage;
import pageobjects.content.FavoritesPage;

public class FirstTest extends BaseTest {
    @Test
    public void OpenApp() {
        FavoritesPage favoritesPage = new FavoritesPage(driver);
        Assert.assertTrue(favoritesPage.isAt()); 
    }
}
