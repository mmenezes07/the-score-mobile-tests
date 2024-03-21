package pageobjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import pageobjects.BasePage;

public class SearchResultsPage extends BasePage {
    private final By searchBar = By.id("search_src_text");
    
    public SearchResultsPage(AppiumDriver driver) {
        super(driver);
    }

    public void inputSearchBar(String searchValue) {
        type(searchBar, searchValue);
    }
    
    public void clickSearchResultByText(String searchValue) {
        By result = By.xpath(STR."(//android.widget.TextView[contains(@text,\"\{searchValue}\")])[1]");
        click(result);
    }
}
