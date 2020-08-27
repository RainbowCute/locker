package com.thoughtworks.locker;

public class Locker {
    private int capacity;

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket save(Bag bag) {
        if(capacity <= 0) {
            throw new FullCapacityException("容量已满");
        }
        return new Ticket();
    }
}
