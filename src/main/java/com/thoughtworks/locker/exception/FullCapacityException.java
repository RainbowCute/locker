package com.thoughtworks.locker.exception;

public class FullCapacityException extends RuntimeException {
    public FullCapacityException(String message) {
        super(message);
    }
}
