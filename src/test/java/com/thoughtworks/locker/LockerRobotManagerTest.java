package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.exception.TicketInvalidException;
import com.thoughtworks.locker.robot.BaseLockerRobot;
import com.thoughtworks.locker.robot.PrimaryLockerRobot;
import com.thoughtworks.locker.robot.SmartLockerRobot;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LockerRobotManagerTest {
    @Test
    public void should_return_ticket_and_save_bag_in_1st_robot_when_locker_robot_manager_save_bag_given_2_robot_exist_free_capacity_and_2_locker_exist_free_capacity() {
        Locker firstLocker = new Locker(10);
        Locker secondLocker = new Locker(10);
        BaseLockerRobot firstRobot = new SmartLockerRobot(Collections.singletonList(new Locker(10)));
        BaseLockerRobot secondRobot = new SmartLockerRobot(Collections.singletonList(new Locker(10)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(firstLocker, secondLocker), Arrays.asList(firstRobot, secondRobot));
        Bag bag = new Bag();

        Ticket ticket = lockerRobotManager.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, firstRobot.take(ticket));
    }

    @Test
    public void should_return_ticket_when_locker_robot_manager_save_bag_given_1_robot_with_free_capacity_and_bag() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(10)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(null, Collections.singletonList(primaryLockerRobot));
        Bag bag = new Bag();

        Ticket ticket = lockerRobotManager.save(bag);

        assertNotNull(ticket);
    }

    @Test
    public void should_return_ticket_when_locker_robot_manager_save_bag_given_1_locker_with_free_capacity_and_bag() {
        Locker locker = new Locker(10);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Collections.singletonList(locker), null);
        Bag bag = new Bag();

        Ticket ticket = lockerRobotManager.save(bag);

        assertNotNull(ticket);
    }

    @Test
    public void should_return_ticket_and_bag_in_2nd_robot_when_locker_robot_manager_save_bag_given_1st_robot_with_full_capacity_and_2nd_robot_with_free_capacity_and_2_locker_with_free_capacity_and_bag() {
        BaseLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(1)));
        BaseLockerRobot smartLockerRobot = new SmartLockerRobot(Collections.singletonList(new Locker(10)));
        Locker firstLocker = new Locker(10);
        Locker secondLocker = new Locker(10);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(firstLocker, secondLocker), Arrays.asList(primaryLockerRobot, smartLockerRobot));
        Bag bag = new Bag();
        primaryLockerRobot.save(new Bag());

        Ticket ticket = lockerRobotManager.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, smartLockerRobot.take(ticket));
    }

    @Test
    public void should_return_ticket_and_bag_in_1st_locker_when_locker_robot_manager_save_bag_given_2_robot_with_full_capacity_and_1st_locker_with_free_capacity_and_2nd_locker_with_free_capacity_and_bag() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(1)));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Collections.singletonList(new Locker(1)));
        primaryLockerRobot.save(new Bag());
        smartLockerRobot.save(new Bag());
        Locker firstLocker = new Locker(10);
        Locker secondLocker = new Locker(10);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(firstLocker, secondLocker), Arrays.asList(primaryLockerRobot, smartLockerRobot));
        Bag bag = new Bag();

        Ticket ticket = lockerRobotManager.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, firstLocker.take(ticket));
    }

    @Test
    public void should_return_ticket_and_bag_in_2nd_locker_when_locker_robot_manager_save_bag_given_2_robot_with_full_capacity_and_1st_locker_with_full_capacity_and_2nd_locker_with_free_capacity_and_bag() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(1)));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot((Collections.singletonList(new Locker(1))));
        primaryLockerRobot.save(new Bag());
        smartLockerRobot.save(new Bag());
        Locker firstLocker = new Locker(1);
        firstLocker.save(new Bag());
        Locker secondLocker = new Locker(10);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(firstLocker, secondLocker), Arrays.asList(primaryLockerRobot, smartLockerRobot));
        Bag bag = new Bag();

        Ticket ticket = lockerRobotManager.save(bag);

        assertNotNull(ticket);
        assertEquals(bag, secondLocker.take(ticket));
    }

    @Test(expected = FullCapacityException.class)
    public void should_throw_full_capacity_exception_when_locker_robot_manager_save_bag_given_2_robot_with_full_capacity_and_2_locker_with_full_capacity_and_bag() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(1)));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot((Collections.singletonList(new Locker(1))));
        primaryLockerRobot.save(new Bag());
        smartLockerRobot.save(new Bag());
        Locker firstLocker = new Locker(1);
        firstLocker.save(new Bag());
        Locker secondLocker = new Locker(1);
        secondLocker.save(new Bag());
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(firstLocker, secondLocker), Arrays.asList(primaryLockerRobot, smartLockerRobot));
        Bag bag = new Bag();

        lockerRobotManager.save(bag);
    }

    @Test
    public void should_return_bag_successfully_when_locker_robot_manager_take_bag_given_1_robot_and_1_locker_and_valid_ticket() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(10)));
        Locker locker = new Locker(10);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Collections.singletonList(locker), Collections.singletonList(primaryLockerRobot));
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.save(bag);

        Bag takenBag = lockerRobotManager.take(ticket);

        assertEquals(bag, takenBag);
    }

    @Test(expected = TicketInvalidException.class)
    public void should_throw_ticket_invalid_exception_when_locker_robot_manager_take_bag_given_1_robot_and_1_locker_and_invalid_ticket() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(10)));
        Locker locker = new Locker(10);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Collections.singletonList(locker), Collections.singletonList(primaryLockerRobot));
        Bag bag = new Bag();
        lockerRobotManager.save(bag);
        Ticket invalidTicket = new Ticket();

        lockerRobotManager.take(invalidTicket);
    }
}
