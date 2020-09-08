package com.thoughtworks.locker;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertNotNull;

public class LockerRobotManagerTest {
    @Test
    public void should_return_ticket_and_save_bag_in_1st_robot_when_locker_robot_manager_save_bag_given_2_robot_exist_free_capacity_and_2_locker_exist_free_capacity() {
        Locker firstLocker = new Locker(10);
        Locker secondLocker = new Locker(10);
        BaseLockerRobot firstRobot = new SmartLockerRobot(Arrays.asList( new Locker(10)));
        BaseLockerRobot secondRobot = new SmartLockerRobot(Arrays.asList( new Locker(10)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(firstLocker, secondLocker), Arrays.asList(firstRobot, secondRobot));
        Bag bag = new Bag();

        Ticket ticket = lockerRobotManager.save(bag);

        assertNotNull(ticket);
    }

    @Test
    public void should_return_ticket_when_locker_robot_manager_save_bag_given_1_robot_with_free_capacity_and_bag() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(new Locker(10)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(null, Collections.singletonList(primaryLockerRobot));
        Bag bag = new Bag();

        Ticket ticket = lockerRobotManager.save(bag);

        assertNotNull(ticket);
    }
}
