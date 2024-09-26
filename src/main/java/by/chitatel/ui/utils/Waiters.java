package by.chitatel.ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Waiters {
    private static final int WAIT_DURATION_SEC = 30;

    public static WebElement waitForElementPresence(WebDriver driver, By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_DURATION_SEC)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static List<WebElement> waitForElementsPresence(WebDriver driver, By by) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_DURATION_SEC)).until(ExpectedConditions.presenceOfElementLocated(by));
        return driver.findElements(by);
    }

    public static WebElement waitForElementBeingClickable(WebDriver driver, By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_DURATION_SEC)).until(ExpectedConditions.elementToBeClickable(by));
    }
}
