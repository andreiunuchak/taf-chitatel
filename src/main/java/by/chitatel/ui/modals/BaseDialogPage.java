package by.chitatel.ui.modals;

import by.chitatel.ui.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;

public abstract class BaseDialogPage {
    protected WebDriver driver;

    public BaseDialogPage() {
        this.driver = DriverSingleton.getWebDriver();
    }
}
