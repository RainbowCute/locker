package com.thoughtworks.locker;

import com.thoughtworks.locker.enums.CapacityTitle;
import com.thoughtworks.locker.report.Capacity;
import com.thoughtworks.locker.report.CapacityItem;
import com.thoughtworks.locker.report.ManagerCapacityReport;
import org.junit.Test;

import java.util.Arrays;
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

    private void initFreeCapacity(Locker locker, int freeCapacity) {
        for (int i = locker.getCapacity(); i > freeCapacity; i--) {
            locker.save(new Bag());
        }
    }
}
