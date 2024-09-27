package by.chitatel.api.endpoints;

import by.chitatel.api.interfaces.GetRequestPerformer;
import by.chitatel.api.interfaces.HeadRequestPerformer;
import by.chitatel.api.interfaces.PostRequestPerformer;
import io.restassured.http.Headers;

public class LoginWithEmail extends Login implements PostRequestPerformer, HeadRequestPerformer, GetRequestPerformer {
    private final String LOGIN_URI_PATH = "/login";

    @Override
    public Headers getHeaders() {
        return super.getHeaders();
    }

    @Override
    public String getUriPath() {
        return LOGIN_URI_PATH;
    }
}
