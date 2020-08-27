package com.thoughtworks.locker.exception;

public class TicketInvalidException extends RuntimeException {

    public TicketInvalidException(String message) {
        super(message);
    }
}
