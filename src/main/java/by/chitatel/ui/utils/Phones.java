package by.chitatel.ui.utils;

import by.chitatel.ui.utils.objects.Phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Phones {
    public static Phone generateValidPhoneNumber(){
        return new Phone(getCorrectCountryCode(), getCorrectOperatorCode(), generatePhoneNumber(7));
    }

    public static Phone generateIncorrectPhoneNumber() {
        return new Phone(getCorrectCountryCode(), generateIncorrectOperatorCode(), generatePhoneNumber(7));
    }

    public static Phone generateInvalidPhoneNumber(int minLength, int maxLength) {
        if (minLength < 0) {
            minLength = 0;
        }
        if (maxLength < minLength) {
            maxLength = minLength;
        }
        int length = (int) (Math.random() * (maxLength - minLength)) + minLength;
        return new Phone(getCorrectCountryCode(), generateIncorrectOperatorCode(), generatePhoneNumber(length));
    }

    public static Phone generateMockPhoneNumber(){
        return new Phone(getCorrectCountryCode(), "00", "0000000");
    }

    private static String getCorrectCountryCode() {
        return "375";
    }

    private static String getCorrectOperatorCode() {
        String[] validOperatorCodes = {"29", "33", "44", "25"};
        return validOperatorCodes[(int) (Math.random() * validOperatorCodes.length)];
    }

    private static String generateIncorrectOperatorCode() {
        List<String> validOperatorCodes = new ArrayList<>(Arrays.asList("29", "33", "44", "25"));
        String incorrectOperatirCode;
        do {
            incorrectOperatirCode = "";
            incorrectOperatirCode += (int) ((Math.random() * 90) + 10);
        } while (validOperatorCodes.contains(incorrectOperatirCode));
        return incorrectOperatirCode;
    }

    private static String generatePhoneNumber(int length) {
        if (length <= 0) {
            return "";
        }
        StringBuilder phoneNumber = new StringBuilder();
        for (int i = 0; i < length; i++) {
            phoneNumber.append((int) (Math.random() * 10));
        }
        return phoneNumber.toString();
    }
}
