package by.chitatel.api;

import by.chitatel.api.endpoints.Login;
import by.chitatel.api.utils.Responses;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected String csrfToken;
    protected Cookies cookies;

    @BeforeEach
    public void getCSRFData() {
        RestAssured.baseURI = new Login().BASE_URL;
        Response response = new Login().performGetRequest();
        csrfToken = Responses.getCSRFToken(response);
        cookies = Responses.getCookies(response);
    }
}
