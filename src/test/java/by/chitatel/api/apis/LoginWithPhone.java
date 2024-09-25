package by.chitatel.api.apis;

import by.chitatel.api.interfaces.RequestPerformer;
import io.restassured.http.Headers;

public class LoginWithPhone extends Login implements RequestPerformer {
    private final String PHONE_LOGIN_URI_PATH = "/login-phone";

    @Override
    public Headers getHeaders() {
        return super.getHeaders();
    }

    @Override
    public String getUriPath() {
        return PHONE_LOGIN_URI_PATH;
    }
}
