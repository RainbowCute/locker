package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.exception.TicketInvalidException;
import com.thoughtworks.locker.robot.BaseLockerRobot;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
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
        return storables.stream()
                .filter(storable -> !storable.isFull())
                .findFirst()
                .map(locker -> locker.save(bag))
                .orElseThrow(FullCapacityException::new);
    }

    public Bag take(Ticket ticket) {
        return storables.stream()
                .filter(locker -> locker.isExist(ticket))
                .findFirst()
                .map(locker -> locker.take(ticket))
                .orElseThrow(TicketInvalidException::new);
    }

    public List<Locker> getLockers() {
        return storables.stream()
                .filter(storable -> storable instanceof Locker)
                .map(item -> (Locker) item)
                .collect(Collectors.toList());
    }

    public List<BaseLockerRobot> getRobots() {
        return storables.stream()
                .filter(storable -> storable instanceof BaseLockerRobot)
                .map(item -> (BaseLockerRobot) item)
                .collect(Collectors.toList());
    }
}
