package com.thoughtworks.locker.report;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CapacityItem {

    private Capacity capacity;
    private List<CapacityItem> childrenCapacity;

    public static CapacityItem from(Capacity capacity, List<CapacityItem> childrenCapacity) {
        return CapacityItem.builder()
                .capacity(capacity)
                .childrenCapacity(childrenCapacity)
                .build();
    }
}
