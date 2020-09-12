package com.thoughtworks.locker.robot;

import com.thoughtworks.locker.Locker;

import java.util.List;
import java.util.Optional;

public class PrimaryLockerRobot extends BaseLockerRobot {
    public PrimaryLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Optional<Locker> getAvailableLocker() {
        return lockers.stream().filter(locker -> !locker.isFull()).findFirst();
    }
}
