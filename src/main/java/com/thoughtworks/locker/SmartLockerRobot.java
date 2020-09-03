package com.thoughtworks.locker;

public class SmartLockerRobot {

    private final Locker locker;

    public SmartLockerRobot(Locker locker) {
        this.locker = locker;
    }

    public Ticket save(Bag bag) {
        return locker.save(bag);
    }
}
