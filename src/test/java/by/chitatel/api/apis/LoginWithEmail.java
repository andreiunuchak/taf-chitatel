package by.chitatel.api.apis;

import by.chitatel.api.interfaces.RequestPerformer;
import io.restassured.http.Headers;

public class LoginWithEmail extends Login implements RequestPerformer {
    private final String LOGIN_URI = "/login";

    @Override
    public Headers getHeaders() {
        return super.getHeaders();
    }

    @Override
    public String getUriPath() {
        return LOGIN_URI;
    }
}
