package com.thoughtworks.locker;

import java.util.List;

public class LockerRobotManager {
    private final List<Locker> lockers;
    private final List<BaseLockerRobot> robots;

    public LockerRobotManager(List<Locker> lockers, List<BaseLockerRobot> robots) {
        this.lockers = lockers;
        this.robots = robots;
    }

    public Ticket save(Bag bag) {
        return robots.stream()
                .filter(robot -> robot.getAvailableLocker().isPresent())
                .findFirst()
                .map(robot -> robot.save(bag))
                .orElse(null);
    }
}
