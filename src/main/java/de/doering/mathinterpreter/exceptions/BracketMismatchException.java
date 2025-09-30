package de.doering.mathinterpreter.exceptions;

public class BracketMismatchException extends RuntimeException {
    public BracketMismatchException() {
    }

    public BracketMismatchException(String message) {
        super(message);
    }

    public BracketMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public BracketMismatchException(Throwable cause) {
        super(cause);
    }

    public BracketMismatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
