package by.chitatel.api.endpoints;

public class LoginWithEmail extends BaseEndpoint {
    private final String LOGIN_EMAIL_URI_PATH = "/login";

    @Override
    public String getUriPath() {
        return LOGIN_EMAIL_URI_PATH;
    }
}
