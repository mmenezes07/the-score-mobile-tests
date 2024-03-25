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

    /**
     * Returns a web element described by a locator 
     * @param by the locator for the element
     * @return a web element
     */
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }
    
    /**
     * Wait and then clicks on an element with locator by
     * @param by the locator for the element
     */
    public void click(By by) {
        waitForElementToBeClickable(by);
        System.out.println(STR."Clicking on element\{by.toString()}");
        findElement(by).click();
    }

    /**
     * Clears and then enters text in element with locator by
     * @param by the locator for the element
     * @param text the text to type
     */
    public void type(By by, String text) {
        waitForElementToBePresent(by);
        System.out.println(STR."Entering text: \{text} in element\{by.toString()}");
        WebElement elem = findElement(by);
        elem.clear();
        elem.sendKeys(text);
    }

    /**
     * Returns the text of an element with locator by
     * @param by the locator for the element
     * @return the text of the element
     */
    public String getText(By by) {
        waitForElementToBePresent(by);
        String text = findElement(by).getText();
        System.out.println(STR."Element\{by.toString()} has text \{text}");
        return text;
    }

    /**
     * Returns true if an element with locator by is selected
     * @param by the locator for the element
     * @return true if an element is selected
     */
    public boolean isElementSelected(By by) {
        waitForElementToBePresent(by);
        boolean selected = findElement(by).isSelected();
        System.out.println(STR."Element\{by.toString()} has property selected set to \{selected}");
        return selected;
    }

    /**
     * Returns true is the element with locator by is displayed. Wait for the element to be present first
     * @param by the locator for the element
     * @return true if the element is displayed
     */
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

    /**
     * Waits for up to DEFAULT_WAIT_TIMEOUT for an element to be clickable
     * @param by the locator for the element
     */
    public void waitForElementToBeClickable(By by) {
        System.out.println(STR."Waiting for element\{by.toString()} to be clickable");
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * Waits for up to DEFAULT_WAIT_TIMEOUT for an element to be present
     * @param by the locator for the element
     */
    public void waitForElementToBePresent(By by) {
        System.out.println(STR."Waiting for element\{by.toString()} to be present");
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_TIMEOUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
