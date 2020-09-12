package com.thoughtworks.locker.robot;

import com.thoughtworks.locker.Bag;
import com.thoughtworks.locker.Locker;
import com.thoughtworks.locker.Storable;
import com.thoughtworks.locker.Ticket;
import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.exception.TicketInvalidException;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Getter
public abstract class BaseLockerRobot implements Storable {
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

    @Override
    public boolean isFull() {
        return lockers.stream().allMatch(Locker::isFull);
    }

    @Override
    public boolean isExist(Ticket ticket) {
        return lockers.stream().anyMatch(locker -> locker.isExist(ticket));
    }
}
