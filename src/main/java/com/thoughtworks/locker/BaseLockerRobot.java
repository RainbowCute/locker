package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.exception.TicketInvalidException;

import java.util.List;
import java.util.Optional;

public abstract class BaseLockerRobot {
    protected final List<Locker> lockers;

    public BaseLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Bag take(Ticket ticket) {
        return lockers.stream()
                .filter(locker -> locker.isExist(ticket))
                .findFirst()
                .map(locker -> locker.take(ticket))
                .orElseThrow(TicketInvalidException::new);
    }

    public abstract Optional<Locker> getAvailableLocker();

    public Ticket save(Bag bag) {
        Optional<Locker> optionalLocker = getAvailableLocker();
        return optionalLocker.map(locker -> locker.save(bag)).orElseThrow(FullCapacityException::new);
    }
}
