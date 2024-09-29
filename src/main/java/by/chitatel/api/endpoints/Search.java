package by.chitatel.api.endpoints;

public class Search extends BaseEndpoint {
    private final String SEARCHNEW_URI_PATH = "/searchnew";

    @Override
    public String getUriPath() {
        return SEARCHNEW_URI_PATH;
    }
}
