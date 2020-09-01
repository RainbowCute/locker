package com.thoughtworks.locker;

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
}
