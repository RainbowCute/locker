package com.thoughtworks.locker.robot;

import com.thoughtworks.locker.Bag;
import com.thoughtworks.locker.Locker;
import com.thoughtworks.locker.Ticket;
import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.exception.TicketInvalidException;
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
    public void should_return_ticket_and_save_bag_in_1st_locker_when_smart_locker_robot_save_bag_given_1st_locker_with_10_capacity_and_2nd_with_5_capacity_and_bag() {
        Locker firstLocker = new Locker(10);
        Locker secondLocker = new Locker(5);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Bag bag = new Bag();

        Ticket ticket = smartLockerRobot.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, firstLocker.take(ticket));
    }

    @Test
    public void should_return_ticket_and_bag_in_2nd_locker_when_smart_locker_robot_save_bag_given_1st_10_capacity_locker_and_2nd_20_capacity_locker_and_bag() {
        Locker firstLocker = new Locker(10);
        Locker secondLocker = new Locker(20);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Bag bag = new Bag();

        Ticket ticket = smartLockerRobot.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, secondLocker.take(ticket));
    }

    @Test
    public void should_return_ticket_and_bag_in_1st_locker_when_smart_locker_robot_save_bag_given_1st_10_capacity_locker_and_2nd_10_capacity_locker_and_bag() {
        Locker firstLocker = new Locker(10);
        Locker secondLocker = new Locker(10);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Bag bag = new Bag();

        Ticket ticket = smartLockerRobot.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, firstLocker.take(ticket));
    }

    @Test(expected = FullCapacityException.class)
    public void should_throw_full_capacity_exception_when_smart_locker_robot_save_bag_given_two_lockers_with_0_free_capacity_and_bag() {
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(1);
        firstLocker.save(new Bag());
        secondLocker.save(new Bag());
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(firstLocker, secondLocker));
        Bag bag = new Bag();

        smartLockerRobot.save(bag);
    }

    @Test
    public void should_take_bag_successfully_when_smart_locker_robot_take_bag_given_1_locker_and_valid_ticket() {
        Locker locker = new Locker(10);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Collections.singletonList(locker));
        Bag bag = new Bag();
        Ticket ticket = smartLockerRobot.save(bag);

        Bag takenBag = smartLockerRobot.take(ticket);

        assertEquals(bag, takenBag);
    }

    @Test(expected = TicketInvalidException.class)
    public void should_throw_ticket_is_invalid_when_smart_locker_robot_take_bag_given_a_locker_invalid_ticket() {
        Locker locker = new Locker(10);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Collections.singletonList(locker));
        smartLockerRobot.save(new Bag());

        smartLockerRobot.take(new Ticket());
    }
}
