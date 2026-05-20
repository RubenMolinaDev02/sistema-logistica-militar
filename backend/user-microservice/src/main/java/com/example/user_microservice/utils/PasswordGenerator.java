package com.example.user_microservice.utils;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+<>?";
    private static final String ALL = LOWER + UPPER + DIGITS + SPECIAL;

    private static final SecureRandom random = new SecureRandom();

    public static String generate(int length) {
        if (length < 8) {
            throw new IllegalArgumentException();
        }

        StringBuilder sb = new StringBuilder(length);

        sb.append(randomChar(LOWER));
        sb.append(randomChar(UPPER));
        sb.append(randomChar(DIGITS));
        sb.append(randomChar(SPECIAL));

        for (int i = sb.length(); i < length; i++) {
            sb.append(ALL.charAt(random.nextInt(ALL.length())));
        }

        return shuffle(sb.toString());
    }

    private static char randomChar(String chars) {
        return chars.charAt(random.nextInt(chars.length()));
    }

    private static String shuffle(String input) {
        char[] arr = input.toCharArray();

        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        return new String(arr);
    }
}