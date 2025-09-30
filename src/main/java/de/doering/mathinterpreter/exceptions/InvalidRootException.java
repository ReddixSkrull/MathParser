package de.doering.mathinterpreter.exceptions;

public class InvalidRootException extends RuntimeException {
    public InvalidRootException() {
        super();
    }

    public InvalidRootException(String message) {
        super(message);
    }

    public InvalidRootException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRootException(Throwable cause) {
        super(cause);
    }

    protected InvalidRootException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
