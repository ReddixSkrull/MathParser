package de.doering.mathinterpreter.util;

import de.doering.mathinterpreter.models.Token;

import java.util.StringJoiner;

public class ArrayUtils {

    public static String arrayToString(Token[] arr) {
        StringJoiner sj = new StringJoiner(",");
        for (Token t : arr) {
            sj.add(t.toString());
        }
        return sj.toString();
    }

    public static String arrayToString(String[] arr) {
        StringJoiner sj = new StringJoiner(", ");
        for (String s : arr) {
            sj.add(s);
        }
        return sj.toString();
    }
}
