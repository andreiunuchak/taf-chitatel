package by.chitatel.api.tests;

import by.chitatel.api.apis.Login;
import by.chitatel.api.utils.Responses;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected String csrfToken;
    protected Cookies cookies;

    @BeforeEach
    public void getCSRFData() {
        Response response = new Login().performRequest();
        csrfToken = Responses.getCSRFToken(response);
        cookies = Responses.getCookies(response);
    }
}
