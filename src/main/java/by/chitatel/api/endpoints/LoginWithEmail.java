package by.chitatel.api.endpoints;

import by.chitatel.api.interfaces.GetRequestPerformer;
import by.chitatel.api.interfaces.HeadRequestPerformer;
import by.chitatel.api.interfaces.PostRequestPerformer;

public class LoginWithEmail extends Login implements PostRequestPerformer, HeadRequestPerformer {
    private final String LOGIN_EMAIL_URI_PATH = "/login";

    @Override
    public String getUriPath() {
        return LOGIN_EMAIL_URI_PATH;
    }
}
