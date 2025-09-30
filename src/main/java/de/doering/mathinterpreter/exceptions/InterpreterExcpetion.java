package de.doering.mathinterpreter.exceptions;

public class InterpreterExcpetion extends RuntimeException {
    public InterpreterExcpetion() {
        super();
    }

    public InterpreterExcpetion(String message) {
        super(message);
    }

    public InterpreterExcpetion(String message, Throwable cause) {
        super(message, cause);
    }

    public InterpreterExcpetion(Throwable cause) {
        super(cause);
    }

    protected InterpreterExcpetion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
