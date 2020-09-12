package com.thoughtworks.locker.robot;

import com.thoughtworks.locker.Locker;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartLockerRobot extends BaseLockerRobot {
    public SmartLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Optional<Locker> getAvailableLocker() {
        return lockers.stream()
                .filter(locker -> !locker.isFull())
                .max(Comparator.comparing(Locker::getFreeCapacity));
    }
}
