package by.chitatel.generators;

import java.util.Random;

public class EmailGenerator {
    private static final String ALLOWED_LOCAL_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!#$%&'*+/=?^_`{|}~-";
    private static final String ALLOWED_DOMAIN_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String INVALID_LOCAL_CHARS = "(),:;<>[]\\\" ";
    private static final String INVALID_DOMAIN_CHARS = " !#$%&'*+/=?^_`{|}~(),:;<>[]\\\"";
    public static final int MAX_ALLOWED_LENGTH = 64;
    public static final int MIN_ALLOWED_LENGTH = 1;

    private static String generateRandomString(String chars, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(new Random().nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static String generateValidEmail() {
        return generateValidEmail(MIN_ALLOWED_LENGTH, MAX_ALLOWED_LENGTH);
    }

    public static String generateValidEmail(int length) {
        length = Math.max(length, 1);
        return generateValidEmail(length, length);
    }

    public static String generateValidEmail(int minLocalPartLength, int maxLocalPartLength) {
        minLocalPartLength = Math.max(minLocalPartLength, 1);
        maxLocalPartLength = Math.max(minLocalPartLength, maxLocalPartLength);
        Random random = new Random();
        String localPart = generateRandomString(ALLOWED_LOCAL_CHARS, maxLocalPartLength == minLocalPartLength ? minLocalPartLength : random.nextInt(maxLocalPartLength - minLocalPartLength) + minLocalPartLength);
        String domainPart = generateRandomString(ALLOWED_DOMAIN_CHARS, random.nextInt(10) + 1);
        String topLevelDomain = generateRandomString(ALLOWED_DOMAIN_CHARS.toLowerCase(), random.nextInt(3) + 2);
        return localPart + "@" + domainPart + "." + topLevelDomain;
    }

    public static String generateInvalidEmail() {
        Random random = new Random();
        String invalidLocalPart = generateRandomString(INVALID_LOCAL_CHARS, random.nextInt(5) + 1);
        String invalidDomainPart = generateRandomString(INVALID_DOMAIN_CHARS, random.nextInt(5) + 1);
        String invalidTopLevelDomain = generateRandomString(INVALID_DOMAIN_CHARS, random.nextInt(2) + 1);

        String[] invalidEmails = {
                invalidLocalPart + "@" + invalidDomainPart + "." + invalidTopLevelDomain,
                invalidLocalPart + "@example.com",
                "user@" + invalidDomainPart + ".com",
                "user@example." + invalidTopLevelDomain,
                "user@.com",
                ".user@example.com",
                "user@com",
                "user@example..com",
                "user@.example.com",
                "user@example.com.",
                "@example.com",
                "user@"
        };

        return invalidEmails[random.nextInt(invalidEmails.length)];
    }
}
