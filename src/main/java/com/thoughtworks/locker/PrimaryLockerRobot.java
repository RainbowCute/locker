package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.FullCapacityException;

import java.util.List;

public class PrimaryLockerRobot {
    private final List<Locker> lockers;

    public PrimaryLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket save(Bag bag) {
        return lockers.stream()
                .filter(Locker::isNotFull)
                .findFirst()
                .map(locker -> locker.save(bag))
                .orElseThrow(() -> new FullCapacityException("储物柜已满"));
    }
}
