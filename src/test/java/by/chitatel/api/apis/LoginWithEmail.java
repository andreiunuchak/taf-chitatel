package by.chitatel.api.apis;

import by.chitatel.api.interfaces.RequestPerformer;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class LoginWithEmail extends Login implements RequestPerformer {
    private final String URI_PATH = "/login";

    @Override
    public Headers getHeaders() {
        return super.getHeaders();
    }

    @Override
    public String getUriPath() {
        return URI_PATH;
    }
}
