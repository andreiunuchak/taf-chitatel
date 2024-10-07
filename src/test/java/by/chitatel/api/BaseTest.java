package by.chitatel.api;

import by.chitatel.api.endpoints.BaseEndpoint;
import by.chitatel.api.utils.Responses;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected String csrfToken;
    protected Cookies cookies;

    @BeforeMethod
    public void getCSRFData() {
        RestAssured.baseURI = new BaseEndpoint().BASE_URL;
        Response response = new BaseEndpoint().performGetRequest();
        csrfToken = Responses.getCSRFToken(response);
        cookies = Responses.getCookies(response);
    }
}
