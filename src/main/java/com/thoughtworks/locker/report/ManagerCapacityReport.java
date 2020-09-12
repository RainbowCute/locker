package com.thoughtworks.locker.report;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ManagerCapacityReport {

    private List<CapacityItem> capacityItems;

    public static ManagerCapacityReport from(List<CapacityItem> capacityItems) {
        return ManagerCapacityReport.builder()
                .capacityItems(capacityItems)
                .build();
    }
}
