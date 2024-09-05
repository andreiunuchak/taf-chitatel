package by.chitatel.ui.utils.objects;

import java.util.Objects;

public class Phone {
    private final String countryCode;
    private final String operatorCode;
    private final String phoneNumber;

    public Phone(String countryCode, String operatorCode, String phoneNumber) {
        this.countryCode = countryCode;
        this.operatorCode = operatorCode;
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPhoneNumberWithOperatorCode() {
        return operatorCode + phoneNumber;
    }

    public String getFullPhoneNumber() {
        return countryCode + operatorCode + phoneNumber;
    }

    public String getFullPhoneNumberFormatted() {
        return String.format("+%s(%s)%s", countryCode, operatorCode, phoneNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone that = (Phone) o;
        return Objects.equals(countryCode, that.countryCode) && Objects.equals(operatorCode, that.operatorCode) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, operatorCode, phoneNumber);
    }
}
