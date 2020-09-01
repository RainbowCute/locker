package com.thoughtworks.locker;

import java.util.List;

public class PrimaryLockerRobot {
    private final List<Locker> lockers;

    public PrimaryLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket save(Bag bag) {
        for (Locker locker : lockers) {
            return locker.save(bag);
        }
        return null;
    }
}
