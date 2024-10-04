package by.chitatel.api.endpoints;

public class Feedback extends BaseEndpoint {
    private final String FEEDBACK_URI_PATH = "/message";

    @Override
    public String getUriPath() {
        return FEEDBACK_URI_PATH;
    }
}
