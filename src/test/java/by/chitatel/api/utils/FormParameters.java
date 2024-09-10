package by.chitatel.api.utils;

import java.util.HashMap;
import java.util.Map;

public class FormParameters {
    private Map<String, Object> formParams = new HashMap<>();

    public FormParameters setEmail(Object email) {
        formParams.put("email", email);
        return this;
    }

    public FormParameters setPassword(Object password) {
        formParams.put("password", password);
        return this;
    }

    public FormParameters setRememberMe(Object rememberMe) {
        formParams.put("remember_me", rememberMe);
        return this;
    }

    public Map<String, Object> build() {
        return formParams;
    }
}
