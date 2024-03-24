package pageobjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected final AppiumDriver driver;
    private static final Duration DEFAULT_WAIT_TIMEOUT = Duration.ofSeconds(20);

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
    }
    
    public void click(By by) {
        waitForElementToBeClickable(by);
        System.out.println(STR."Clicking on element\{by.toString()}");
        driver.findElement(by).click();
    }
    
    public void type(By by, String text) {
        waitForElementToBePresent(by);
        System.out.println(STR."Entering text: \{text} in element\{by.toString()}");
        WebElement elem = driver.findElement(by);
        elem.clear();
        elem.sendKeys(text);
    }
    
    public String getText(By by) {
        waitForElementToBePresent(by);
        String text = driver.findElement(by).getText();
        System.out.println(STR."Element\{by.toString()} has text \{text}");
        return text;
    }

    public boolean isElementSelected(By by) {
        waitForElementToBePresent(by);
        boolean selected = driver.findElement(by).isSelected();
        System.out.println(STR."Element\{by.toString()} has property selected set to \{selected}");
        return selected;
    }

    public boolean isElementDisplayed(By by) {
        try {
            waitForElementToBePresent(by);
        } catch (Exception e) {
            return false;
        }
        
        boolean displayed = driver.findElement(by).isDisplayed();
        System.out.println(STR."Element\{by.toString()} has property displayed set to \{displayed}");
        return displayed;
    }

    public void waitForElementToBeClickable(By by) {
        System.out.println(STR."Waiting for element\{by.toString()} to be clickable");
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitForElementToBePresent(By by) {
        System.out.println(STR."Waiting for element\{by.toString()} to be present");
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_TIMEOUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForElementToBeVisible(By by) {
        System.out.println(STR."Waiting for element\{by.toString()} to be visible");
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForText(By by, String text) {
        System.out.println(STR."Waiting for element\{by.toString()} to have text \{text}");
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_TIMEOUT);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }
}
