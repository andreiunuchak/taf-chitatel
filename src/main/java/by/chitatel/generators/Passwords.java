package by.chitatel.generators;

import java.util.Random;

public class Passwords {
    public final static int MIN_ALLOWED_LENGTH = 6;
    public final static int MAX_ALLOWED_LENGTH = 255;

    private static final char[] ALL_CHARS = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-',
            '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^',
            '_', '`', '{', '|', '}', '~'
    };

    public static String generatePassword() {
        return generatePassword(new Random().nextInt(MAX_ALLOWED_LENGTH - MIN_ALLOWED_LENGTH) + MIN_ALLOWED_LENGTH);
    }

    public static String generatePassword(int length) {
        if (length <= 0) {
            return "";
        }
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(ALL_CHARS[new Random().nextInt(ALL_CHARS.length)]);
        }
        return password.toString();
    }
}
