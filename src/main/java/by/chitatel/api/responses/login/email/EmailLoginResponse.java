package by.chitatel.api.responses.login.email;

public class EmailLoginResponse {

    private EmailLoginErrors errors;

    public EmailLoginErrors getErrors() {
        return errors;
    }

    public void setErrors(EmailLoginErrors errors) {
        this.errors = errors;
    }
}
