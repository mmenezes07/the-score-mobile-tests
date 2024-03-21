package pageobjects.content;

import io.appium.java_client.AppiumDriver;
import pageobjects.BasePage;

public class FavoritesPage extends ContentBasePage {
    
    public FavoritesPage(AppiumDriver driver) {
        super(driver);
    }
    
    public boolean isAt() {
        return isElementSelected(favoritesTab);
    }
}
