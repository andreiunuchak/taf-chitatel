package by.chitatel.api;

import by.chitatel.api.endpoints.Search;
import by.chitatel.api.utils.Responses;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("API Tests")
@Feature("API Tests of search")
public class SearchTest extends BaseTest {

    @Test
    @DisplayName("API GET Test of searching product")
    public void testBookSearch() {
        String productName = "A Game of Thrones: Book 1 of a Song of Ice and Fire";
        Response response = new Search().performGetRequest(productName);
        int statusCode = response.statusCode();

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertTrue(Responses.getSearchedProducts(response).getFirst().contains(productName))
        );
    }
}
