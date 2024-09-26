package by.chitatel.ui.pages;

import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {
    private final String BASE_URL = "https://chitatel.by/cart";
    private final By cartItemBy = By.xpath("//div[@class='w-cart-body']/div");
    private final By cartItemTitleBy = By.xpath(".//div[3]/a/div[1]");
    private final By cartItemPriceBy = By.xpath(".//div[7]/div[3]");

    @Override
    public CartPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public List<WebElement> getProductsInCart() {
        return Waiters.waitForElementsPresence(driver, cartItemBy);
    }

    public String getProductTitleInCart(int index) {
        return getProductsInCart().get(index).findElement(cartItemTitleBy).getText();
    }

    public String getProductPriceInCart(int index) {
        return getProductsInCart().get(index).findElement(cartItemPriceBy).getText();
    }
}
