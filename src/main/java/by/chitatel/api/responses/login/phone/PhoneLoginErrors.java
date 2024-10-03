package by.chitatel.api.responses.login.phone;

import java.util.List;

public class PhoneLoginErrors {

    private List<String> tel;
    private List<String> password_phone;
    private String nouser;

    public List<String> getPhoneError() {
        return tel;
    }

    public List<String> getPasswordError() {
        return password_phone;
    }

    public String getNoUserError() {
        return nouser;
    }
}
