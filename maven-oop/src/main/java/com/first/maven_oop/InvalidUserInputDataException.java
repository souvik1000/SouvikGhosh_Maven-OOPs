package com.first.maven_oop;

@SuppressWarnings("serial")
public class InvalidUserInputDataException extends RuntimeException {

    public InvalidUserInputDataException() {
        super();
    }

    public InvalidUserInputDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserInputDataException(String message) {
        super(message);
    }

}
