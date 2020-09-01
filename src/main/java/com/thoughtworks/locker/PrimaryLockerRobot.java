package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.exception.TicketInvalidException;

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

    public Bag take(Ticket ticket) {
        return lockers.stream()
                .filter(locker -> locker.isExist(ticket))
                .findFirst()
                .map(locker -> locker.take(ticket))
                .orElseThrow(() -> new TicketInvalidException("无效票据"));
    }
}
