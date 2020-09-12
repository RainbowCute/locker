package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.exception.TicketInvalidException;
import com.thoughtworks.locker.robot.BaseLockerRobot;

import java.util.ArrayList;
import java.util.List;

public class LockerRobotManager {
    private List<Storable> storables = new ArrayList<>();

    public LockerRobotManager(List<Locker> lockers, List<BaseLockerRobot> robots) {
        if (robots != null) {
            storables.addAll(robots);
        }
        if (lockers != null) {
            storables.addAll(lockers);
        }
    }

    public Ticket save(Bag bag) {
        return  storables.stream()
                .filter(storable -> !storable.isFull())
                .findFirst()
                .map(locker -> locker.save(bag))
                .orElseThrow(FullCapacityException::new);
    }

    public Bag take(Ticket ticket) {
        return  storables.stream()
                .filter(locker -> locker.isExist(ticket))
                .findFirst()
                .map(locker -> locker.take(ticket))
                .orElseThrow(TicketInvalidException::new);
    }
}
