package by.chitatel.api.responses.login.email;

import java.util.LinkedHashMap;
import java.util.Map;

public class EmailLoginResponse {

    private EmailLoginErrors errors;
    private final Map<String, Object> additionalProperties = new LinkedHashMap<>();

    public EmailLoginErrors getErrors() {
        return errors;
    }

    public void setErrors(EmailLoginErrors errors) {
        this.errors = errors;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}