package by.chitatel.api;

import by.chitatel.api.endpoints.Search;
import by.chitatel.api.utils.Responses;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SearchTest extends BaseTest {
    @Test
    public void testBookSearch() {
        String productName = "A Game of Thrones: Book 1 of a Song of Ice and Fire";

        Response response = new Search().performGetRequest(productName);

        Assertions.assertTrue(Responses.getSearchedProducts(response).getFirst().contains(productName));
    }
}
