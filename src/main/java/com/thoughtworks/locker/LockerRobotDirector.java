package com.thoughtworks.locker;

import com.thoughtworks.locker.enums.CapacityTitle;
import com.thoughtworks.locker.report.Capacity;
import com.thoughtworks.locker.report.CapacityItem;
import com.thoughtworks.locker.report.ManagerCapacityReport;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LockerRobotDirector {

    public ManagerCapacityReport countCapacityReport(LockerRobotManager lockerRobotManager) {
        List<CapacityItem> childrenCapacityItems = lockerRobotManager.getLockers().stream().
                map(locker -> {
                    Capacity capacity = Capacity.from(CapacityTitle.L, locker.getCapacity(), locker.getFreeCapacity());
                    return CapacityItem.from(capacity, null);
                })
                .collect(Collectors.toList());
        Capacity managerCapacity = getCapacityFromChildren(childrenCapacityItems);
        CapacityItem capacityItem = CapacityItem.from(managerCapacity, childrenCapacityItems);
        return ManagerCapacityReport.from(Collections.singletonList(capacityItem));
    }

    private Capacity getCapacityFromChildren(List<CapacityItem> capacityItems) {
        int totalCapacity = capacityItems.stream().mapToInt(item -> item.getCapacity().getTotalCapacity()).sum();
        int freeCapacity = capacityItems.stream().mapToInt(item -> item.getCapacity().getFreeCapacity()).sum();
        return Capacity.from(CapacityTitle.M, totalCapacity, freeCapacity);
    }

}
