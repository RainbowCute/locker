package com.thoughtworks.locker;

import com.thoughtworks.locker.enums.CapacityTitle;
import com.thoughtworks.locker.report.Capacity;
import com.thoughtworks.locker.report.CapacityItem;
import com.thoughtworks.locker.report.ManagerCapacityReport;
import com.thoughtworks.locker.robot.PrimaryLockerRobot;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LockerRobotDirectorTest {

    @Test
    public void should_return_successful_result_when_director_count_report_given_locker_robot_manager_manage_two_lockers() {
        Locker firstLocker = new Locker(10);
        Locker secondLocker = new Locker(8);
        initFreeCapacity(firstLocker, 5);
        initFreeCapacity(secondLocker, 3);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(firstLocker, secondLocker), null);
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector();

        ManagerCapacityReport managerCapacityReport = lockerRobotDirector.countCapacityReport(lockerRobotManager);

        Capacity managerCapacity = managerCapacityReport.getCapacityItems().get(0).getCapacity();
        List<CapacityItem> lockerCapacityOfManager = managerCapacityReport.getCapacityItems().get(0).getChildrenCapacity();
        assertNotNull(managerCapacityReport);
        assertEquals(1, managerCapacityReport.getCapacityItems().size());
        assertEquals(CapacityTitle.M, managerCapacity.getTitle());
        assertEquals(8, managerCapacity.getFreeCapacity());
        assertEquals(18, managerCapacity.getTotalCapacity());
        assertEquals(2, lockerCapacityOfManager.size());
        assertEquals(CapacityTitle.L, lockerCapacityOfManager.get(0).getCapacity().getTitle());
        assertEquals(5, lockerCapacityOfManager.get(0).getCapacity().getFreeCapacity());
        assertEquals(10, lockerCapacityOfManager.get(0).getCapacity().getTotalCapacity());
        assertEquals(CapacityTitle.L, lockerCapacityOfManager.get(1).getCapacity().getTitle());
        assertEquals(3, lockerCapacityOfManager.get(1).getCapacity().getFreeCapacity());
        assertEquals(8, lockerCapacityOfManager.get(1).getCapacity().getTotalCapacity());

    }

    @Test
    public void should_return_correct_result_when_director_count_report_given_locker_robot_manager_manage_two_robots() {
        Locker firstRobotManagedLocker = new Locker(10);
        Locker secondRobotManagedLocker = new Locker(9);
        initFreeCapacity(firstRobotManagedLocker, 5);
        initFreeCapacity(secondRobotManagedLocker, 4);
        PrimaryLockerRobot firstLockerRobot = new PrimaryLockerRobot(Collections.singletonList(firstRobotManagedLocker));
        PrimaryLockerRobot secondLockerRobot = new PrimaryLockerRobot(Collections.singletonList(secondRobotManagedLocker));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(null, Arrays.asList(firstLockerRobot, secondLockerRobot));
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector();

        ManagerCapacityReport managerCapacityReport = lockerRobotDirector.countCapacityReport(lockerRobotManager);

        Capacity managerCapacity = managerCapacityReport.getCapacityItems().get(0).getCapacity();
        List<CapacityItem> robotCapacitiesOfManager = managerCapacityReport.getCapacityItems().get(0).getChildrenCapacity();
        List<CapacityItem> lockerCapacitiesOfFirstRobot = robotCapacitiesOfManager.get(0).getChildrenCapacity();
        List<CapacityItem> lockerCapacitiesOfSecondRobot = robotCapacitiesOfManager.get(1).getChildrenCapacity();
        assertNotNull(managerCapacityReport);
        assertEquals(1, managerCapacityReport.getCapacityItems().size());
        assertEquals(CapacityTitle.M, managerCapacity.getTitle());
        assertEquals(9, managerCapacity.getFreeCapacity());
        assertEquals(19, managerCapacity.getTotalCapacity());
        assertEquals(2, robotCapacitiesOfManager.size());
        assertEquals(CapacityTitle.R, robotCapacitiesOfManager.get(0).getCapacity().getTitle());
        assertEquals(5, robotCapacitiesOfManager.get(0).getCapacity().getFreeCapacity());
        assertEquals(10, robotCapacitiesOfManager.get(0).getCapacity().getTotalCapacity());
        assertEquals(1, lockerCapacitiesOfFirstRobot.size());
        assertEquals(CapacityTitle.L, lockerCapacitiesOfFirstRobot.get(0).getCapacity().getTitle());
        assertEquals(5, lockerCapacitiesOfFirstRobot.get(0).getCapacity().getFreeCapacity());
        assertEquals(10, lockerCapacitiesOfFirstRobot.get(0).getCapacity().getTotalCapacity());
        assertEquals(CapacityTitle.R, robotCapacitiesOfManager.get(1).getCapacity().getTitle());
        assertEquals(4, robotCapacitiesOfManager.get(1).getCapacity().getFreeCapacity());
        assertEquals(9, robotCapacitiesOfManager.get(1).getCapacity().getTotalCapacity());
        assertEquals(1, lockerCapacitiesOfSecondRobot.size());
        assertEquals(CapacityTitle.L, lockerCapacitiesOfSecondRobot.get(0).getCapacity().getTitle());
        assertEquals(4, lockerCapacitiesOfSecondRobot.get(0).getCapacity().getFreeCapacity());
        assertEquals(9, lockerCapacitiesOfSecondRobot.get(0).getCapacity().getTotalCapacity());
    }

    @Test
    public void should_return_successful_result_when_director_count_report_given_locker_robot_manager_mange_one_robot_and_this_robot_mange_two_lockers() {
        Locker firstLocker = new Locker(8);
        Locker secondLocker = new Locker(11);
        initFreeCapacity(firstLocker, 3);
        initFreeCapacity(secondLocker, 6);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(firstLocker, secondLocker));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(null, Collections.singletonList(primaryLockerRobot));
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector();

        ManagerCapacityReport managerCapacityReport = lockerRobotDirector.countCapacityReport(lockerRobotManager);

        Capacity managerCapacity = managerCapacityReport.getCapacityItems().get(0).getCapacity();
        List<CapacityItem> childrenCapacityOfManager = managerCapacityReport.getCapacityItems().get(0).getChildrenCapacity();
        List<CapacityItem> childrenCapacityOfRobot = managerCapacityReport.getCapacityItems().get(0).getChildrenCapacity().get(0).getChildrenCapacity();
        assertNotNull(managerCapacityReport);
        assertEquals(1, managerCapacityReport.getCapacityItems().size());
        assertEquals(CapacityTitle.M, managerCapacity.getTitle());
        assertEquals(9, managerCapacity.getFreeCapacity());
        assertEquals(19, managerCapacity.getTotalCapacity());
        assertEquals(1, childrenCapacityOfManager.size());
        assertEquals(CapacityTitle.R, childrenCapacityOfManager.get(0).getCapacity().getTitle());
        assertEquals(9, childrenCapacityOfManager.get(0).getCapacity().getFreeCapacity());
        assertEquals(19, childrenCapacityOfManager.get(0).getCapacity().getTotalCapacity());
        assertEquals(2, childrenCapacityOfRobot.size());
        assertEquals(CapacityTitle.L, childrenCapacityOfRobot.get(0).getCapacity().getTitle());
        assertEquals(3, childrenCapacityOfRobot.get(0).getCapacity().getFreeCapacity());
        assertEquals(8, childrenCapacityOfRobot.get(0).getCapacity().getTotalCapacity());
        assertEquals(CapacityTitle.L, childrenCapacityOfRobot.get(1).getCapacity().getTitle());
        assertEquals(6, childrenCapacityOfRobot.get(1).getCapacity().getFreeCapacity());
        assertEquals(11, childrenCapacityOfRobot.get(1).getCapacity().getTotalCapacity());
    }

    private void initFreeCapacity(Locker locker, int freeCapacity) {
        for (int i = locker.getCapacity(); i > freeCapacity; i--) {
            locker.save(new Bag());
        }
    }
}
