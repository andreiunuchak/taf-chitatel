package by.chitatel.ui.pages;

import org.openqa.selenium.WebDriver;

public abstract class AbstractBasePage {
    protected WebDriver driver;

    public AbstractBasePage(WebDriver driver) {
        this.driver = driver;
    }

    public abstract AbstractBasePage openPage();
}
