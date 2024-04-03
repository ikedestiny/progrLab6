package org.example.common.exception;

/**
 * throws if entered value was ctrl+d
 */
public class InvalidInputException extends Exception {

    public InvalidInputException() {
        System.out.println(getMessage());
    }

    public String getMessage() {
        return "Invalid input. Work with the collection will be finished";
    }
}
