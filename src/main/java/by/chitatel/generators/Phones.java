package by.chitatel.generators;

import by.chitatel.ui.enums.CountryCodes;
import by.chitatel.ui.enums.OperatorCodes;
import by.chitatel.ui.enums.PhoneNumbers;
import by.chitatel.generators.objects.Phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Phones {
    private static final String VALID_COUNTRY_CODE = CountryCodes.BELARUS.getCode();
    private static final List<String> VALID_OPERATOR_CODES = new ArrayList<>(Arrays.asList(OperatorCodes.VELCOM_OLD.getCode(), OperatorCodes.MTS.getCode(), OperatorCodes.VELCOM_NEW.getCode(), OperatorCodes.LIFE.getCode()));
    private static final String MOCKED_OPERATOR_CODE = OperatorCodes.MOCKED.getCode();
    private static final String MOCKED_PHONE_NUMBER = PhoneNumbers.MOCKED.getCode();

    private static final Random RANDOM = new Random();

    public static Phone generateValidPhoneNumber() {
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
        int length = RANDOM.nextInt(maxLength - minLength) + minLength;
        return new Phone(getCorrectCountryCode(), generateIncorrectOperatorCode(), generatePhoneNumber(length));
    }

    public static Phone generateMockPhoneNumber() {
        return new Phone(getCorrectCountryCode(), MOCKED_OPERATOR_CODE, MOCKED_PHONE_NUMBER);
    }

    private static String getCorrectCountryCode() {
        return VALID_COUNTRY_CODE;
    }

    private static String getCorrectOperatorCode() {
        return VALID_OPERATOR_CODES.get(RANDOM.nextInt(VALID_OPERATOR_CODES.size()));
    }

    private static String generateIncorrectOperatorCode() {
        String incorrectOperatirCode;
        do {
            incorrectOperatirCode = "";
            incorrectOperatirCode += RANDOM.nextInt(90) + 10;
        } while (VALID_OPERATOR_CODES.contains(incorrectOperatirCode));
        return incorrectOperatirCode;
    }

    private static String generatePhoneNumber(int length) {
        length = Math.max(length, 1);
        StringBuilder phoneNumber = new StringBuilder();
        for (int i = 0; i < length; i++) {
            phoneNumber.append(RANDOM.nextInt(10));
        }
        return phoneNumber.toString();
    }
}
