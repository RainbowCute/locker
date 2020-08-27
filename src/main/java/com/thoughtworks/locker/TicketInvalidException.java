package com.thoughtworks.locker;

public class TicketInvalidException extends RuntimeException {

    public TicketInvalidException(String message) {
        super(message);
    }
}
