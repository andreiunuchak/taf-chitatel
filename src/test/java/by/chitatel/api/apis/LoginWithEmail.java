package by.chitatel.api.apis;

import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

import java.util.Map;

public class LoginWithEmail extends Login {
    private final String URI_PATH = "/login";

    public Response performRequestWithParamsTokenCookies(Map<String, Object> formParams, String csrfToken, Cookies cookies) {
        return RestAssured
                .given()
                .headers(getHeaders())
                .cookies(cookies)
                .formParams(formParams)
                .formParam("_token", csrfToken)
                .when()
                .post(URI_PATH);
    }

    public Response performRequestWithParamsToken(Map<String, Object> formParams, String csrfToken) {
        return RestAssured
                .given()
                .headers(getHeaders())
                .formParams(formParams)
                .formParam("_token", csrfToken)
                .when()
                .post(URI_PATH);
    }

    public Response performRequestWithParamsCookies(Map<String, Object> formParams, Cookies cookies) {
        return RestAssured
                .given()
                .headers(getHeaders())
                .cookies(cookies)
                .formParams(formParams)
                .when()
                .post(URI_PATH);
    }

    public Response performRequestWithParamsTokenCookies(String csrfToken, Cookies cookies) {
        return RestAssured
                .given()
                .headers(getHeaders())
                .cookies(cookies)
                .formParam("_token", csrfToken)
                .when()
                .post(URI_PATH);
    }

    public Response performRequestWithParams(Map<String, Object> formParams) {
        System.out.println(formParams);
        return RestAssured
                .given()
                .headers(getHeaders())
                .formParams(formParams)
                .when()
                .post(URI_PATH);
    }

    public Response performRequestWithToken(String csrfToken) {
        return RestAssured
                .given()
                .headers(getHeaders())
                .formParam("_token", csrfToken)
                .when()
                .post(URI_PATH);
    }

    public Response performRequestWithCookies(Cookies cookies) {
        return RestAssured
                .given()
                .headers(getHeaders())
                .cookies(cookies)
                .when()
                .post(URI_PATH);
    }
}
