package by.chitatel.ui.pages;

import by.chitatel.ui.driver.DriverSingleton;
import by.chitatel.ui.modals.SearchModal;
import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver driver;
    private final By searchFieldBy = By.xpath("//input[@id='input-search']");
    private final By cartButtonBy = By.xpath("//div[@class='h-cart']");
    private final By cartCountBy = By.xpath("//div[@class='h-cart']//div[@class='count']");

    public BasePage() {
        this.driver = DriverSingleton.getWebDriver();
    }

    public abstract BasePage openPage();

    public SearchModal typeIntoSearchField(String text) {
        Waiters.waitForElementPresence(driver, searchFieldBy).sendKeys(text);
        return new SearchModal();
    }

    public CartPage clickOnCartButton() {
        Waiters.waitForElementPresence(driver, cartButtonBy).click();
        return new CartPage();
    }

    protected void waitForCartUpdate(String initialAmountOfItemsInCart) {
        int attempt = 0;
        while (getAmountOfItemsInCart().equals(initialAmountOfItemsInCart) && attempt < 10) {
            attempt++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected String getAmountOfItemsInCart() {
        return Waiters.waitForElementPresence(driver, cartCountBy).getText();
    }
}
