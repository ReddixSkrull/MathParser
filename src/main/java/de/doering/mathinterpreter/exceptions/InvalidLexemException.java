package de.doering.mathinterpreter.exceptions;

public class InvalidLexemException extends RuntimeException{
    public InvalidLexemException() {
        super();
    }

    public InvalidLexemException(String message) {
        super(message);
    }

    public InvalidLexemException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidLexemException(Throwable cause) {
        super(cause);
    }

    protected InvalidLexemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
