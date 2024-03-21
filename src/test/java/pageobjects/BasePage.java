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
        System.out.println(STR."Clicking on element\{by.toString()}");
        waitForElementToBePresent(by);
        driver.findElement(by).click();
    }

    public void waitForElementToBePresent(By by) {
        System.out.println(STR."Wating for element\{by.toString()} to be present");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
