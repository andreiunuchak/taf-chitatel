package by.chitatel.api.responses.login.phone;

public class PhoneLoginResponse {

    private PhoneLoginErrors errors;

    public PhoneLoginErrors getErrors() {
        return errors;
    }

    public void setErrors(PhoneLoginErrors errors) {
        this.errors = errors;
    }
}