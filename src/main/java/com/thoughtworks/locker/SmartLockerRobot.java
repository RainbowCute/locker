package com.thoughtworks.locker;

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
                .filter(Locker::isNotFull)
                .max(Comparator.comparing(Locker::getFreeCapacity));
    }
}
