package by.chitatel.api.endpoints;

public class LoginWithPhone extends BaseEndpoint {
    private final String LOGIN_PHONE_URI_PATH = "/login-phone";

    @Override
    public String getUriPath() {
        return LOGIN_PHONE_URI_PATH;
    }
}
