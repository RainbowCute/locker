package com.thoughtworks.locker.report;

import com.thoughtworks.locker.enums.CapacityTitle;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Capacity {

    private CapacityTitle title;
    private int totalCapacity;
    private int freeCapacity;

    public static Capacity from(CapacityTitle title, int totalCapacity, int freeCapacity) {
        return Capacity.builder()
                .title(title)
                .totalCapacity(totalCapacity)
                .freeCapacity(freeCapacity)
                .build();
    }
}
