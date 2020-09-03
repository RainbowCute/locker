package com.thoughtworks.locker;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SmartLockerRobotTest {

    @Test
    public void should_return_ticket_when_smart_locker_robot_save_bag_given_a_locker_with_10_capacity_and_bag() {
        Locker locker = new Locker(10);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(locker);
        Bag bag = new Bag();

        Ticket ticket = smartLockerRobot.save(bag);

        assertNotNull(ticket);
    }
}
