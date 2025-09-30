package de.doering.mathinterpreter.models;

public class TokenFactory {

    public static Token createDigitToken(int line, int column, String value) {
        return new Token(Tokentypes.DIGIT,line,column,value);
    }

    public static Token createOperationToken(int line, int column, String value) {
        return new Token(Tokentypes.OPERATION,line,column,value);
    }

    public static Token createOpeningBracketToken(int line, int column, String value) {
        return new Token(Tokentypes.OPEN_BRACKET,line,column,value);
    }

    public static Token createClosingBracketToken(int line, int column, String value) {
        return new Token(Tokentypes.CLOSED_BRACKET,line,column,value);
    }

    public static Token createUnknownToken(int line, int column, String value) {
        return new Token(Tokentypes.UNKNOWN,line,column,value);
    }

}
