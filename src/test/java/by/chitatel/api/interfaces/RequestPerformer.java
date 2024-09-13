package by.chitatel.api.interfaces;

import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.Map;

public interface RequestPerformer {
    Headers getHeaders();

    String getUriPath();
    default Response performRequest(Map<String, Object> formParams, String csrfToken, Cookies cookies) {
        return RestAssured
                .given()
                .headers(getHeaders())
                .cookies(cookies)
                .formParams(formParams)
                .formParam("_token", csrfToken)
                .when()
                .post(getUriPath());
    }

    default Response performRequest(Map<String, Object> formParams, String csrfToken) {
        return RestAssured
                .given()
                .headers(getHeaders())
                .formParams(formParams)
                .formParam("_token", csrfToken)
                .when()
                .post(getUriPath());
    }

    default Response performRequest(Map<String, Object> formParams, Cookies cookies) {
        return RestAssured
                .given()
                .headers(getHeaders())
                .cookies(cookies)
                .formParams(formParams)
                .when()
                .post(getUriPath());
    }

    default Response performRequest(String csrfToken, Cookies cookies) {
        return RestAssured
                .given()
                .headers(getHeaders())
                .cookies(cookies)
                .formParam("_token", csrfToken)
                .when()
                .post(getUriPath());
    }

    default Response performRequest(Map<String, Object> formParams) {
        System.out.println(formParams);
        return RestAssured
                .given()
                .headers(getHeaders())
                .formParams(formParams)
                .when()
                .post(getUriPath());
    }

    default Response performRequest(String csrfToken) {
        return RestAssured
                .given()
                .headers(getHeaders())
                .formParam("_token", csrfToken)
                .when()
                .post(getUriPath());
    }

    default Response performRequest(Cookies cookies) {
        return RestAssured
                .given()
                .headers(getHeaders())
                .cookies(cookies)
                .when()
                .post(getUriPath());
    }

    default Response performRequest() {
        return RestAssured
                .given()
                .headers(getHeaders())
                .when()
                .post(getUriPath());
    }
}
