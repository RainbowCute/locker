package com.thoughtworks.locker;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SmartLockerRobotTest {

    @Test
    public void should_return_ticket_when_smart_locker_robot_save_bag_given_a_locker_with_10_capacity_and_bag() {
        Locker locker = new Locker(10);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Collections.singletonList(locker));
        Bag bag = new Bag();

        Ticket ticket = smartLockerRobot.save(bag);

        assertNotNull(ticket);
    }

    @Test
    public void should_return_ticket_and_save_bag_in_1st_locker_when_smart_locker_robot_save_bag_given_1st_locker_with_10_capacity_and_2st_with_5_capacity_and_bag() {
        Locker firstLocker = new Locker(10);
        Locker secondLocker = new Locker(5);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Bag bag = new Bag();

        Ticket ticket = smartLockerRobot.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, firstLocker.take(ticket));
    }
}
