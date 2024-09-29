package by.chitatel.api.endpoints;

import by.chitatel.api.interfaces.GetRequestPerformer;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Login extends BaseEndpoint implements GetRequestPerformer {
    private final String URI_PATH = "/";

    @Override
    public Headers getHeaders() {
        return new Headers(
                new Header("user-agent", DEFAULT_USER_AGENT)
        );
    }

    @Override
    public String getUriPath() {
        return URI_PATH;
    }
}
