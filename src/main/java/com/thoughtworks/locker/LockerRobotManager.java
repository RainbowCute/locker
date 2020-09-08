package com.thoughtworks.locker;

import java.util.ArrayList;
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
                             lockers.stream())
                .findFirst()
                .map(locker -> locker.save(bag))
                .orElse(null);
    }
}
