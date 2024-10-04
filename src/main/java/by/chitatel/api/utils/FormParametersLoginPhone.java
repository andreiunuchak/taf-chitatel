package by.chitatel.api.utils;

public class FormParametersLoginPhone extends FormParameters {
    public FormParametersLoginPhone setPhone(Object phone) {
        formParams.put("tel", phone);
        return this;
    }

    public FormParametersLoginPhone setPhonePassword(Object password) {
        formParams.put("password_phone", password);
        return this;
    }

    public FormParametersLoginPhone setPhoneRememberMe(Object rememberMe) {
        formParams.put("remember_me_phone", rememberMe);
        return this;
    }
}
