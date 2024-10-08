package by.chitatel.ui.pages;

import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;

public class ProductPage extends BasePage {
    private final String PRODUCT_PAGE_URL = BASE_URL + "/catalog/book";
    private final By titleBy = By.xpath("//div[@class='content']/h1");
    private final By priceBy = By.xpath("//div[@class='price new  has-discount']");
    private final By addToCartButtonBy = By.xpath("//div[contains(@class, 'button medium')]");

    @Override
    public ProductPage openPage() {
        driver.navigate().to(PRODUCT_PAGE_URL);
        return this;
    }

    public String getTitle() {
        return Waiters.waitForElementPresence(titleBy).getText();
    }

    public String getPrice() {
        return Waiters.waitForElementPresence(priceBy).getText();
    }

    public ProductPage clickOnAddToCartButton() {
        String initialAmountOfItemsInCart = getAmountOfItemsInCart();
        Waiters.waitForElementPresence(addToCartButtonBy).click();
        String expectedAmountOfItemsInCart = String.valueOf(Integer.parseInt(initialAmountOfItemsInCart) + 1);
        waitForCartUpdate(expectedAmountOfItemsInCart);
        return this;
    }
}
