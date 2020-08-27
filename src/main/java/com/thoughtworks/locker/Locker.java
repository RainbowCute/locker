package com.thoughtworks.locker;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Locker {
    private int freeCapacity;
    private final Map<Ticket, Bag> ticketBagMap = new HashMap<>();

    public Locker(int freeCapacity) {
        this.freeCapacity = freeCapacity;
    }

    public Locker() {

    }

    public Ticket save(Bag bag) {
        if (freeCapacity <= 0) {
            throw new FullCapacityException("储物柜已满");
        }

        Ticket ticket = new Ticket();
        ticketBagMap.put(ticket, bag);
        return ticket;
    }

    public Bag take(Ticket ticket) {
        Bag bag = ticketBagMap.get(ticket);
        if(Objects.isNull(bag)) {
            throw new IllegalTicketException("非法票据");
        }
        return bag;
    }
}
