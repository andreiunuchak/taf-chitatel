package by.chitatel.api.utils;

import by.chitatel.api.responses.login.email.EmailLoginErrors;
import by.chitatel.api.responses.login.email.EmailLoginResponse;
import by.chitatel.api.responses.login.phone.PhoneLoginErrors;
import by.chitatel.api.responses.login.phone.PhoneLoginResponse;
import io.restassured.response.Response;

public class Errors {
    public static EmailLoginErrors getErrorsFromEmailLoginResponse(Response response) {
        return response.then().extract().as(EmailLoginResponse.class).getErrors();
    }

    public static PhoneLoginErrors getErrorsFromPhoneLoginResponse(Response response) {
        response.then().log().body();
        return response.then().extract().as(PhoneLoginResponse.class).getErrors();
    }
}
