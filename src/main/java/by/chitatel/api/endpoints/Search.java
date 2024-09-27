package by.chitatel.api.endpoints;

import by.chitatel.api.interfaces.GetRequestPerformer;
import io.restassured.http.Header;
import io.restassured.http.Headers;

public class Search implements GetRequestPerformer {
    private final String URI_PATH = "/searchnew";

    @Override
    public Headers getHeaders() {
        return new Headers(
                new Header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36")
        );
    }

    @Override
    public String getUriPath() {
        return URI_PATH;
    }
}
