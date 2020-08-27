package com.thoughtworks.locker;

public class Locker {
    private int capacity;

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket save(Bag bag) {
        return new Ticket();
    }
}
