package by.chitatel.ui.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverSingleton {
    //    private static WebDriver driver;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverSingleton() {
    }

    public static WebDriver getWebDriver() {
        if (driver.get() == null) {
            String browser = System.getProperty("browser");
            browser = browser != null ? browser : "chrome";
            switch (browser) {
                case "firefox":
                    driver.set(new FirefoxDriver());
                    break;
                case "edge":
                    driver.set(new EdgeDriver());
                    break;
                case "safari":
                    driver.set(new SafariDriver());
                    break;
                case "chrome":
                default:
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-search-engine-choice-screen");
                    driver.set(new ChromeDriver(options));
                    break;
            }
        }
        driver.get().manage().window().maximize();
        return driver.get();
    }

    public static void closeWebDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
