package com.skills.tute.utils;

public abstract class StStringUtils {

    public static boolean isBlank(String in) {
        return in == null || in.isBlank();
    }

    public static boolean isNotBlank(String in) {
        return !(in == null || in.isBlank());
    }

    public static String firstCharCaps(String input) {
        if (isBlank(input)) {
            return input;
        }
        input = input.trim().toLowerCase();
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
