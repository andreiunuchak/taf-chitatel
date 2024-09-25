package by.chitatel.generators.enums;

public enum OperatorCodes {
    VELCOM_OLD("29"), MTS("33"), VELCOM_NEW("44"), LIFE("25"), MOCKED("00");

    private final String code;

    OperatorCodes(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
