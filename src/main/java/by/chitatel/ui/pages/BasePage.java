package by.chitatel.ui.pages;

import by.chitatel.ui.driver.DriverSingleton;
import by.chitatel.ui.modals.SearchDialogPage;
import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver driver;
    private final int DEFAULT_WAIT_TIME_MILLISECONDS = 1000;
    private final int MAX_CART_UPDATE_ATTEMPTS = 10;
    protected final String BASE_URL = "https://chitatel.by";
    private final By searchFieldBy = By.xpath("//input[@id='input-search']");
    private final By cartButtonBy = By.xpath("//div[@class='h-cart']");
    private final By cartCountBy = By.xpath("//div[@class='h-cart']//div[@class='count']");

    public BasePage() {
        this.driver = DriverSingleton.getWebDriver();
    }

    public abstract BasePage openPage();

    public SearchDialogPage typeIntoSearchField(String text) {
        Waiters.waitForElementPresence(searchFieldBy).sendKeys(text);
        return new SearchDialogPage();
    }

    public CartPage clickOnCartButton() {
        Waiters.waitForElementPresence(cartButtonBy).click();
        return new CartPage();
    }

    protected void waitForCartUpdate(String expectedAmountOfItemsInCart) {
        Waiters.waitForElementTextToBe(cartCountBy, expectedAmountOfItemsInCart);
    }

    protected String getAmountOfItemsInCart() {
        return Waiters.waitForElementPresence(cartCountBy).getText();
    }
}
