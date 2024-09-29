package by.chitatel.generators.enums;

public enum RememberMeCodes {
    SELECTED("1"), NOT_SELECTED("0");

    private final String code;

    RememberMeCodes(String value) {
        this.code = value;
    }

    public String getCode() {
        return code;
    }
}
