package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.exception.TicketInvalidException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class LockerRobotManager {
    private List<Locker> lockers = new ArrayList<>();
    private List<BaseLockerRobot> robots = new ArrayList<>();

    public LockerRobotManager(List<Locker> lockers, List<BaseLockerRobot> robots) {
        if (lockers != null) {
            this.lockers = lockers;
        }
        if (robots != null) {
            this.robots = robots;
        }
    }

    public Ticket save(Bag bag) {
        return Stream.concat(robots.stream()
                                   .filter(robot -> robot.getAvailableLocker().isPresent())
                                   .map(robot -> robot.getAvailableLocker().get()),
                             lockers.stream().filter(Locker::isNotFull))
                .findFirst()
                .map(locker -> locker.save(bag))
                .orElseThrow(FullCapacityException::new);
    }

    public Bag take(Ticket ticket) {
        return Stream.concat(robots.stream()
                                   .map(BaseLockerRobot::getLockers)
                                   .flatMap(Collection::stream),
                             lockers.stream())
                .filter(locker -> locker.isExist(ticket))
                .findFirst()
                .map(locker -> locker.take(ticket))
                .orElseThrow(TicketInvalidException::new);
    }
}
