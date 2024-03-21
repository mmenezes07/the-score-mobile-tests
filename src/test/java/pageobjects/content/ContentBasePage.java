package pageobjects.content;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import pageobjects.BasePage;

public class ContentBasePage extends BasePage {
    private final By searchBar = By.id("search_bar_text_view");
    protected final By favoritesTab = By.id("navigation_favorites");
    protected final By leaguesTab = By.id("navigation_leagues");
            
    public ContentBasePage(AppiumDriver driver) {
        super(driver);
    }
    
    public void clickSearchBar() {
        click(searchBar);
    }

    public void clickFavoritesTab() {
        click(favoritesTab);
    }
    
    public void clickLeaguesTab() {
        click(leaguesTab);
    }
}
