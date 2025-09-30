package de.doering;

public class TokenFactory {

    public static Token createDigitToken(int line, int column, String value) {
        return new Token("digit",line,column,value);
    }

    public static Token createOperationToken(int line, int column, String value) {
        return new Token("operation",line,column,value);
    }

    public static Token createOpeningBracketToken(int line, int column, String value) {
        return new Token("opening_bracket",line,column,value);
    }

    public static Token createClosingBracketToken(int line, int column, String value) {
        return new Token("closing_bracket",line,column,value);
    }

    public static Token createUnknownToken(int line, int column, String value) {
        return new Token("unknown",line,column,value);
    }

}
