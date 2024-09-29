package by.chitatel.api.endpoints;

import by.chitatel.api.interfaces.GetRequestPerformer;
import by.chitatel.api.interfaces.HeadRequestPerformer;
import by.chitatel.api.interfaces.PostRequestPerformer;
import io.restassured.http.Header;
import io.restassured.http.Headers;

public class BaseEndpoint implements GetRequestPerformer, PostRequestPerformer, HeadRequestPerformer {
    public final String BASE_URL = "https://chitatel.by";
    private final String URI_PATH = "/";
    protected final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36";

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
