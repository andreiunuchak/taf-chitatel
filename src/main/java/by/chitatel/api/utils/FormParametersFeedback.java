package by.chitatel.api.utils;

public class FormParametersFeedback extends FormParameters {
    public FormParametersFeedback setName(Object rememberMe) {
        formParams.put("message_name", rememberMe);
        return this;
    }

    public FormParametersFeedback setPhoneNumber(Object phoneNumber) {
        formParams.put("message_phone", phoneNumber);
        return this;
    }

    public FormParametersFeedback setTheme(Object theme) {
        formParams.put("message_theme", theme);
        return this;
    }

    public FormParametersFeedback setMessage(Object message) {
        formParams.put("message_note", message);
        return this;
    }

    public FormParametersFeedback setWorkEmail(Object workEmail) {
        formParams.put("workemail", workEmail);
        return this;
    }
}
