package by.chitatel.api.apis;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Login {
    private final String BASE_URL = "https://chitatel.by";
    private final String URI_PATH = "/";

    protected Headers getHeaders() {
        return new Headers(
                new Header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36")
        );
    }

    public Response execute() {
        RestAssured.baseURI = new Login().BASE_URL;
        return RestAssured
                .given()
                .headers(getHeaders())
                .when()
                .get(URI_PATH);
    }
}
