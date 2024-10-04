package by.chitatel.ui.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverSingleton {
    private static WebDriver driver;

    private DriverSingleton() {
    }

    public static WebDriver getWebDriver() {
        if (driver == null) {
            String browser = System.getProperty("browser");
            switch (browser){
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                case "chrome":
                default:
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-search-engine-choice-screen");
                    options.addArguments("--start-maximized");
                    driver = new ChromeDriver(options);
                    break;
            }
        }
        return driver;
    }

    public static void closeWebDriver() {
        driver.quit();
        driver = null;
    }
}
