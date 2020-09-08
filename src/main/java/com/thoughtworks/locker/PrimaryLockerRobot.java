package com.thoughtworks.locker;

import java.util.List;
import java.util.Optional;

public class PrimaryLockerRobot extends BaseLockerRobot {
    public PrimaryLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Optional<Locker> getAvailableLocker() {
        return lockers.stream().filter(Locker::isNotFull).findFirst();
    }
}
