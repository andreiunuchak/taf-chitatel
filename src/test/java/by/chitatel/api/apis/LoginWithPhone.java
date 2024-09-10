package by.chitatel.api.apis;

import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

import java.util.HashMap;

public class LoginWithPhone extends Login {
    private final String URI_PATH = "/login-phone";
    private final HashMap<String, String> formParams = new HashMap<>();
    private final Cookies csrfCookies;

    public LoginWithPhone(String phone, String password, boolean rememberMe, String csrfToken, Cookies cookies) {
        formParams.put("tel", phone);
        formParams.put("password_phone", password);
        formParams.put("remember_me_phone", rememberMe ? "1" : "0");
        formParams.put("_token", csrfToken);
        this.csrfCookies = cookies;
    }

    public Response execute() {
        return RestAssured
                .given()
                .headers(getHeaders())
                .cookies(csrfCookies)
                .formParams(formParams)
                .when()
                .post(URI_PATH);
    }
}
