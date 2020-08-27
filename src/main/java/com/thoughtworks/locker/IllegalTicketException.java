package com.thoughtworks.locker;

public class IllegalTicketException extends RuntimeException {

    public IllegalTicketException(String message) {
        super(message);
    }
}
