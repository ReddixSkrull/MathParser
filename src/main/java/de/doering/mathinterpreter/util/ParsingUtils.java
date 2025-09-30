package de.doering.mathinterpreter.util;

import java.util.Arrays;

public class ParsingUtils {
    public static String[] removeWhitespaces(String[] arr) {
        return Arrays.stream(arr).filter(s -> !s.isBlank()).toArray(String[]::new);
    }

    public static boolean isDigit(String c) {
        char cast = c.charAt(0);
        return cast >= '0' && cast <= '9';
    }

    public static boolean isOperation(String c) {
        char cast = c.charAt(0);
        return cast == '+' || cast == '*' || cast == '/' || cast == '-';
    }

    public static boolean isOpeningBracket(String c) {
        char cast = c.charAt(0);
        return cast == '(' || cast == '[' || cast == '{';
    }

    public static boolean isClosingBracket(String c) {
        char cast = c.charAt(0);
        return cast == ']' || cast == '}' || cast == ')';
    }
}
