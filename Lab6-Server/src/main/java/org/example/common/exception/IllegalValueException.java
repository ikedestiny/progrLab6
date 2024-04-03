package org.example.common.exception;

public class IllegalValueException extends Exception {
    private final String message;

    public IllegalValueException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
