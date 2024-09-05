package by.chitatel.ui.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Phones {
    public static String getCorrectCountryCode() {
        return "+375";
    }

    public static String getCorrectOperatorCode() {
        String[] validOperatorCodes = {"29", "33", "44", "25"};
        return validOperatorCodes[(int) (Math.random() * validOperatorCodes.length)];
    }

    public static String generateIncorrectOperatorCode() {
        List<String> validOperatorCodes = new ArrayList<>(Arrays.asList("29", "33", "44", "25"));
        String incorrectOperatirCode;
        do {
            incorrectOperatirCode = "";
            incorrectOperatirCode += (int) ((Math.random() * 90) + 10);
        } while (validOperatorCodes.contains(incorrectOperatirCode));
        return incorrectOperatirCode;
    }

    public static String getMockedOperatorCode() {
        return "00";
    }

    public static String getMockedPhoneNumber() {
        return "0000000";
    }

    public static String generatePhoneNumber(int length) {
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
