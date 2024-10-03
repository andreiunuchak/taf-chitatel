package by.chitatel.api.responses.login.email;

import java.util.List;

public class EmailLoginErrors {

    private List<String> email;
    private List<String> password;
    private String nouser;

    public List<String> getEmailError() {
        return email;
    }

    public List<String> getPasswordError() {
        return password;
    }

    public String getNoUserError() {
        return nouser;
    }
}
