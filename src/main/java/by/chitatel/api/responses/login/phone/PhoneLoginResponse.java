package by.chitatel.api.responses.login.phone;

import java.util.LinkedHashMap;
import java.util.Map;

public class PhoneLoginResponse {

    private PhoneLoginErrors errors;
    private final Map<String, Object> additionalProperties = new LinkedHashMap<>();

    public PhoneLoginErrors getErrors() {
        return errors;
    }

    public void setErrors(PhoneLoginErrors errors) {
        this.errors = errors;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}