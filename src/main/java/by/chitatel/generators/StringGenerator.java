package by.chitatel.generators;

import java.util.Random;

public class StringGenerator {
    private final static String characters = "abcdefghijklmnopqrstuvwxyz";

    public static String generateString(int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(new Random().nextInt(characters.length())));
        }
        return result.toString();
    }
}
