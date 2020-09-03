package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.FullCapacityException;

import java.util.Comparator;
import java.util.List;

public class SmartLockerRobot {

    private final List<Locker> lockers;

    public SmartLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket save(Bag bag) {
        return lockers.stream()
                .max(Comparator.comparing(Locker::getFreeCapacity))
                .map(locker -> locker.save(bag))
                .orElseThrow(FullCapacityException::new);
    }
}
