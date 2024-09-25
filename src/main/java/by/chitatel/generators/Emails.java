package by.chitatel.generators;

import java.util.Random;

public class Emails {
    private static final String ALLOWED_LOCAL_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!#$%&'*+/=?^_`{|}~-";
    private static final String ALLOWED_DOMAIN_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String INVALID_LOCAL_CHARS = "(),:;<>[]\\\" ";
    private static final String INVALID_DOMAIN_CHARS = " !#$%&'*+/=?^_`{|}~(),:;<>[]\\\"";

    private static String generateRandomString(String chars, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(new Random().nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static String generateValidEmail() {
        return generateValidEmail(1, 10);
    }

    public static String generateValidEmail(int length) {
        length = Math.max(length, 1);
        return generateValidEmail(length, length);
    }

    public static String generateValidEmail(int minLocalPartLength, int maxLocalPartLength) {
        minLocalPartLength = Math.max(minLocalPartLength, 1);
        maxLocalPartLength = Math.max(minLocalPartLength, maxLocalPartLength);
        Random random = new Random();
        String localPart = generateRandomString(ALLOWED_LOCAL_CHARS, maxLocalPartLength == minLocalPartLength ? 0 : random.nextInt(maxLocalPartLength - minLocalPartLength) + minLocalPartLength);
        String domainPart = generateRandomString(ALLOWED_DOMAIN_CHARS, random.nextInt(10) + 1);
        String topLevelDomain = generateRandomString(ALLOWED_DOMAIN_CHARS.toLowerCase(), random.nextInt(3) + 2);
        return localPart + "@" + domainPart + "." + topLevelDomain;
    }

    public static String generateInvalidEmail() {
        Random random = new Random();
        String invalidLocalPart = generateRandomString(INVALID_LOCAL_CHARS, random.nextInt(5) + 1); // Длина от 1 до 5 символов
        String invalidDomainPart = generateRandomString(INVALID_DOMAIN_CHARS, random.nextInt(5) + 1); // Длина от 1 до 5 символов
        String invalidTopLevelDomain = generateRandomString(INVALID_DOMAIN_CHARS, random.nextInt(2) + 1); // Длина от 1 до 2 символов

        // Формирование некорректного email с разными ошибками
        String[] invalidEmails = {
                invalidLocalPart + "@" + invalidDomainPart + "." + invalidTopLevelDomain, // Некорректные символы в обеих частях
                invalidLocalPart + "@example.com",  // Некорректная локальная часть
                "user@" + invalidDomainPart + ".com", // Некорректный домен
                "user@example." + invalidTopLevelDomain, // Некорректный TLD
                "user@.com",  // Домен начинается с точки
                ".user@example.com", // Локальная часть начинается с точки
                "user@com", // Доменная часть без точки
                "user@example..com", // Две точки подряд в доменной части
                "user@.example.com", // Точка в начале доменной части
                "user@example.com.", // Точка в конце доменной части
                "@example.com", // Отсутствие локальной части
                "user@" // Отсутствие доменной части
        };

        return invalidEmails[random.nextInt(invalidEmails.length)];
    }
}
