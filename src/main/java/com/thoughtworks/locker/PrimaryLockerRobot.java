package com.thoughtworks.locker;

public class PrimaryLockerRobot {
    private Locker locker;

    public PrimaryLockerRobot(Locker locker) {
        this.locker = locker;
    }

    public Ticket save(Bag bag) {
        return locker.save(bag);
    }
}
