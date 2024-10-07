package by.chitatel.api;

import by.chitatel.api.endpoints.Search;
import by.chitatel.api.utils.Responses;
import by.chitatel.names.EpicNames;
import by.chitatel.names.FeatureNames;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Epic(EpicNames.API)
@Feature(FeatureNames.API_SEARCH)
public class SearchTest extends BaseTest {

    @Test(description = "API GET Test of searching product")
    public void testBookSearch() {
        String productName = "A Game of Thrones: Book 1 of a Song of Ice and Fire";
        Response response = new Search().performGetRequest(productName);
        int statusCode = response.statusCode();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200);
        softAssert.assertTrue(Responses.getSearchedProducts(response).getFirst().contains(productName));
        softAssert.assertAll();
    }
}
