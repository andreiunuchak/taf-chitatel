package by.chitatel.api.endpoints;

import by.chitatel.api.interfaces.GetRequestPerformer;
import io.restassured.http.Header;
import io.restassured.http.Headers;

public class Search extends BaseEndpoint implements GetRequestPerformer {
    private final String SEARCHNEW_URI_PATH = "/searchnew";

    @Override
    public Headers getHeaders() {
        return new Headers(
                new Header("user-agent", DEFAULT_USER_AGENT)
        );
    }

    @Override
    public String getUriPath() {
        return SEARCHNEW_URI_PATH;
    }
}
