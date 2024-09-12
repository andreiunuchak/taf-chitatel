package by.chitatel.api.responses.login.phone;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PhoneLoginErrors {

    private String tel;
    private List<String> password_phone;
    private String nouser;
    private final Map<String, Object> additionalProperties = new LinkedHashMap<>();

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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}