package by.chitatel.api.utils;

public class FormParametersLoginEmail extends FormParameters {
    public FormParametersLoginEmail setEmail(Object email) {
        formParams.put("email", email);
        return this;
    }

    public FormParametersLoginEmail setPassword(Object password) {
        formParams.put("password", password);
        return this;
    }

    public FormParametersLoginEmail setRememberMe(Object rememberMe) {
        formParams.put("remember_me", rememberMe);
        return this;
    }
}
