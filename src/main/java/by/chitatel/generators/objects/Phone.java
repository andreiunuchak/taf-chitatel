package by.chitatel.generators.objects;

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
        return String.format("%s%s", operatorCode, phoneNumber);
    }

    public String getPhoneNumberFull() {
        return String.format("%s%s%s", countryCode, operatorCode, phoneNumber);
    }

    public String getPhoneNumberFullFormatted() {
        return String.format("+%s(%s)%s", countryCode, operatorCode, phoneNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(countryCode, phone.countryCode) && Objects.equals(operatorCode, phone.operatorCode) && Objects.equals(phoneNumber, phone.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, operatorCode, phoneNumber);
    }
}
