package by.chitatel.api.endpoints;

import by.chitatel.api.interfaces.PostRequestPerformer;

public class LoginWithPhone extends Login implements PostRequestPerformer {
    private final String LOGIN_PHONE_URI_PATH = "/login-phone";

    @Override
    public String getUriPath() {
        return LOGIN_PHONE_URI_PATH;
    }
}
