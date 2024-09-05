package by.chitatel.ui.modals;

import org.openqa.selenium.WebDriver;

public abstract class AbstractBaseModal {
    protected WebDriver driver;

    public AbstractBaseModal(WebDriver driver) {
        this.driver = driver;
    }
}
