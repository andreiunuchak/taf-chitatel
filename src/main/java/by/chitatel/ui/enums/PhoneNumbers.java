package by.chitatel.ui.enums;

public enum PhoneNumbers {
    MOCKED("0000000");

    private final String code;

    PhoneNumbers(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
