package by.chitatel.ui.modals;

import by.chitatel.ui.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;

public abstract class BaseModal {
    protected WebDriver driver;

    public BaseModal() {
        this.driver = DriverSingleton.getWebDriver();
    }
}
