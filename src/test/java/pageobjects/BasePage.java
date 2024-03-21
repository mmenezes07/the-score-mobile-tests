package pageobjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private final AppiumDriver driver;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
    }

    public void click(By by) {
        waitForElementToBeClickable(by);
        System.out.println(STR."Clicking on element\{by.toString()}");
        driver.findElement(by).click();
    }

    public void waitForElementToBeClickable(By by) {
        System.out.println(STR."Wating for element\{by.toString()} to be clickable");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitForElementToBePresent(By by) {
        System.out.println(STR."Wating for element\{by.toString()} to be present");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    
    public boolean isElementSelected(By by) {
        waitForElementToBePresent(by);
        boolean selected = driver.findElement(by).isSelected();
        System.out.println(STR."Element\{by.toString()} has property selected set to \{selected}");
        return selected;
    }
}
