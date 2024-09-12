package by.chitatel.api.responses.login.email;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EmailLoginErrors {

    private List<String> email;
    private List<String> password;
    private String nouser;
    private final Map<String, Object> additionalProperties = new LinkedHashMap<>();

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

    public String getNouserError() {
        return nouser;
    }

    public void setNouserError(String nouser) {
        this.nouser = nouser;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}