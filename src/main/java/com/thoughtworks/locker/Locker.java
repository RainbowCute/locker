package com.thoughtworks.locker;

import java.util.HashMap;
import java.util.Map;

public class Locker {
    private final int freeCapacity;
    private final Map<Ticket, Bag> ticketBagMap = new HashMap<>();

    public Locker(int freeCapacity) {
        this.freeCapacity = freeCapacity;
    }

    public Ticket save(Bag bag) {
        if(freeCapacity <= 0) {
            throw new FullCapacityException("容量已满");
        }

        Ticket ticket = new Ticket();
        ticketBagMap.put(ticket, bag);
        return ticket;
    }

    public Bag take(Ticket ticket) {
        return ticketBagMap.get(ticket);
    }
}
