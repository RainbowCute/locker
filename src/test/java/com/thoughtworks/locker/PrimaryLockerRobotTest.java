package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.FullCapacityException;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PrimaryLockerRobotTest {
    @Test
    public void should_return_ticket_when_save_bag_given_primary_locker_robot_manage_one_locker_with_10_free_capacity_and_bag() {
        Locker locker = new Locker(10);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(locker);
        Bag bag = new Bag();

        Ticket ticket = primaryLockerRobot.save(bag);

        assertNotNull(ticket);
    }

    @Test(expected = FullCapacityException.class)
    public void should_throw_full_capacity_exception_when_save_bag_given_primary_locker_robot_manage_one_locker_with_0_free_capacity_and_bag() {
        Locker locker = new Locker(1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(locker);
        primaryLockerRobot.save(new Bag());
        Bag bag = new Bag();

        primaryLockerRobot.save(bag);
    }
}
