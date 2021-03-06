package com.thoughtworks.locker;

import com.thoughtworks.locker.enums.CapacityTitle;
import com.thoughtworks.locker.report.Capacity;
import com.thoughtworks.locker.report.CapacityItem;
import com.thoughtworks.locker.report.ManagerCapacityReport;
import com.thoughtworks.locker.robot.BaseLockerRobot;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LockerRobotDirector {

    private final List<LockerRobotManager> lockerRobotManagers;
    private final List<Storable> storables;

    public LockerRobotDirector(List<LockerRobotManager> lockerRobotManagers, List<Storable> storables) {
        this.lockerRobotManagers = lockerRobotManagers;
        this.storables = storables;
    }

    public ManagerCapacityReport countCapacityReport() {
        List<CapacityItem> capacityItemList = lockerRobotManagers.stream()
                .map(this::getManagerCapacityItem)
                .collect(Collectors.toList());
        return ManagerCapacityReport.from(capacityItemList);
    }

    private CapacityItem getManagerCapacityItem(LockerRobotManager lockerRobotManager) {
        List<BaseLockerRobot> robots = lockerRobotManager.getRobots();
        List<Locker> lockers = lockerRobotManager.getLockers();

        List<CapacityItem> capacityItems = Stream.concat(robots.stream()
                                                               .map(robot -> getCapacityItemFromChildren(robot.getLockers())),
                                                         from(lockers).stream())
                                                 .collect(Collectors.toList());

        Capacity managerCapacity = getCapacityFromChildren(capacityItems, CapacityTitle.M);

        return CapacityItem.from(managerCapacity, capacityItems);
    }

    private CapacityItem getCapacityItemFromChildren(List<Locker> lockers) {
        List<CapacityItem> capacityItems = from(lockers);
        Capacity capacity = getCapacityFromChildren(capacityItems, CapacityTitle.R);
        return CapacityItem.from(capacity, capacityItems);
    }

    private List<CapacityItem> from(List<Locker> lockers) {
        return lockers.stream().
                map(locker -> {
                    Capacity capacity = Capacity.from(CapacityTitle.L, locker.getCapacity(), locker.getFreeCapacity());
                    return CapacityItem.from(capacity, null);
                })
                .collect(Collectors.toList());
    }

    private Capacity getCapacityFromChildren(List<CapacityItem> capacityItems, CapacityTitle parentTitle) {
        int totalCapacity = capacityItems.stream().mapToInt(item -> item.getCapacity().getTotalCapacity()).sum();
        int freeCapacity = capacityItems.stream().mapToInt(item -> item.getCapacity().getFreeCapacity()).sum();
        return Capacity.from(parentTitle, totalCapacity, freeCapacity);
    }

}
