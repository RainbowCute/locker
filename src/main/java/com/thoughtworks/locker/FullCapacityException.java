package com.thoughtworks.locker;

public class FullCapacityException extends RuntimeException {
    public FullCapacityException(String message) {
        super(message);
    }
}
