package by.chitatel.api.responses.login.email;

import java.util.List;

public class EmailLoginErrors {

    private List<String> email;
    private List<String> password;
    private String nouser;

    public List<String> getEmailError() {
        return email;
    }

    public void setEmailError(List<String> email) {
        this.email = email;
    }

    public List<String> getPasswordError() {
        return password;
    }

    public void setPasswordError(List<String> password) {
        this.password = password;
    }

    public String getNoUserError() {
        return nouser;
    }

    public void setNouserError(String nouser) {
        this.nouser = nouser;
    }
}
