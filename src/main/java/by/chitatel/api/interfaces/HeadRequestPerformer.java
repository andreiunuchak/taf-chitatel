package by.chitatel.api.interfaces;

import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.Map;

public interface HeadRequestPerformer {
    Headers getHeaders();

    String getUriPath();

    default Response performHeadRequest(Map<String, Object> formParams, String csrfToken, Cookies cookies) {
        return RestAssured
                .given()
                .headers(getHeaders())
                .cookies(cookies)
                .formParams(formParams)
                .formParam("_token", csrfToken)
                .when()
                .get(getUriPath());
    }
}
