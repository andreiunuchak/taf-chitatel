package by.chitatel.ui;

import by.chitatel.names.EpicNames;
import by.chitatel.names.FeatureNames;
import by.chitatel.ui.pages.HomePage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic(EpicNames.UI)
@Feature(FeatureNames.UI_CART)
public class CartTest extends BaseTest {

    @Test
    @DisplayName("UI Test of adding product to the cart")
    public void testAddProductToCart() {
        String productName = "A Game of Thrones: Book 1 of a Song of Ice and Fire";
        new HomePage()
                .openPage()
                .typeIntoSearchField(productName)
                .clickOnSearchResultItem(0)
                .clickOnAddToCartButton();
        String productNameInCart = new HomePage()
                .clickOnCartButton()
                .getProductTitleInCart(0);

        Assertions.assertEquals(productName, productNameInCart);
    }
}
