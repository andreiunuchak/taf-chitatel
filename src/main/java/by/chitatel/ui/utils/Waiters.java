package by.chitatel.ui.utils;

import by.chitatel.ui.driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Waiters {
    private static final int DEFAULT_WAIT_DURATION_SECONDS = 30;

    public static WebElement waitForElementPresence(By by) {
        return new WebDriverWait(DriverSingleton.getWebDriver(), Duration.ofSeconds(DEFAULT_WAIT_DURATION_SECONDS)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static List<WebElement> waitForElementsPresence(By by) {
        new WebDriverWait(DriverSingleton.getWebDriver(), Duration.ofSeconds(DEFAULT_WAIT_DURATION_SECONDS)).until(ExpectedConditions.presenceOfElementLocated(by));
        return DriverSingleton.getWebDriver().findElements(by);
    }

    public static WebElement waitForElementBeingClickable(By by) {
        return new WebDriverWait(DriverSingleton.getWebDriver(), Duration.ofSeconds(DEFAULT_WAIT_DURATION_SECONDS)).until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitForElementTextToBe(By by, String value) {
        new WebDriverWait(DriverSingleton.getWebDriver(), Duration.ofSeconds(DEFAULT_WAIT_DURATION_SECONDS)).until(ExpectedConditions.textToBe(by, value));
    }
}
