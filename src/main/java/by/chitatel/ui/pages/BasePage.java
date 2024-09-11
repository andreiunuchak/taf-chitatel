package by.chitatel.ui.pages;

import by.chitatel.ui.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver driver;
    protected final String BASE_URL = "https://chitatel.by/password/reset";

    public BasePage() {
        this.driver = DriverSingleton.getWebDriver();
    }

    public abstract BasePage openPage();
}
