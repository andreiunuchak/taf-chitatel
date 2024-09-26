package by.chitatel.ui.pages;

import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;

public class ProductPage extends BasePage{
    private final String BASE_URL = "https://chitatel.by/catalog/book";
    private final By titleBy = By.xpath("//div[@class='content']/h1");
    private final By priceBy = By.xpath("//div[@class='price new  has-discount']");
    private final By addToCartButtonBy = By.xpath("//div[contains(@class, 'button medium')]");

    @Override
    public ProductPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public String getTitle(){
        return Waiters.waitForElementPresence(driver, titleBy).getText();
    }

    public String getPrice(){
        return Waiters.waitForElementPresence(driver, priceBy).getText();
    }

    public ProductPage clickOnAddToCartButton(){
        String initialAmountOfItemsInCart = getAmountOfItemsInCart();
        Waiters.waitForElementPresence(driver, addToCartButtonBy).click();
        waitForCartUpdate(initialAmountOfItemsInCart);
        return this;
    }
}
