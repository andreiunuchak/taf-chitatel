package by.chitatel.ui;

import by.chitatel.ui.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CartTest extends BaseTest{

    @Test
    public void testAddProductToCart(){
            String productName = "A Game of Thrones: Book 1 of a Song of Ice and Fire";
            new HomePage()
                    .openPage()
                    .typeIntoSearchField(productName)
                    .clickOnSearchResultItem(0)
                    .clickOnAddToCartButton();
            String actualTitle = new HomePage()
                    .clickOnCartButton()
                    .getProductTitleInCart(0);
            Assertions.assertEquals(productName, actualTitle);
    }
}
