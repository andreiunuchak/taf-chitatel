package by.chitatel.api.responses.login.phone;

import java.util.List;

public class PhoneLoginErrors {

    private String tel;
    private List<String> password_phone;
    private String nouser;

    public String getPhoneError() {
        return tel;
    }

    public void setPhoneError(String tel) {
        this.tel = tel;
    }

    public List<String> getPasswordError() {
        return password_phone;
    }

    public void setPasswordError(List<String> password_phone) {
        this.password_phone = password_phone;
    }

    public String getNouserError() {
        return nouser;
    }

    public void setNouserError(String nouser) {
        this.nouser = nouser;
    }
}