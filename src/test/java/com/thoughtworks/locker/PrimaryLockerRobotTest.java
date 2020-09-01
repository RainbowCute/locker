package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.FullCapacityException;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PrimaryLockerRobotTest {
    @Test
    public void should_return_ticket_when_save_bag_given_primary_locker_robot_manage_one_locker_with_10_free_capacity_and_bag() {
        Locker locker = new Locker(10);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker));
        Bag bag = new Bag();

        Ticket ticket = primaryLockerRobot.save(bag);

        assertNotNull(ticket);
    }

    @Test(expected = FullCapacityException.class)
    public void should_throw_full_capacity_exception_when_save_bag_given_primary_locker_robot_manage_one_locker_with_0_free_capacity_and_bag() {
        Locker locker = new Locker(1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker));
        primaryLockerRobot.save(new Bag());
        Bag bag = new Bag();

        primaryLockerRobot.save(bag);
    }

    @Test
    public void should_return_ticket_and_save_bag_in_first_locker_when_save_bag_given_primary_locker_robot_and_1st_locker_with_10_free_capacity_and_2nd_locker_with_10_free_capacity_and_bag() {
        Locker firstLocker = new Locker(10);
        Locker secondLocker = new Locker(10);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Bag bag = new Bag();

        Ticket ticket = primaryLockerRobot.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, firstLocker.take(ticket));
    }

    @Test
    public void should_return_ticket_and_bag_in_second_locker_when_save_bag_given_primary_locker_robot_and_1st_0_capacity_locker_and_2nd_10_capacity_locker_and_bag() {
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(10);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(firstLocker, secondLocker));
        firstLocker.save(new Bag());
        Bag bag = new Bag();

        Ticket ticket = primaryLockerRobot.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, secondLocker.take(ticket));
    }
}
