package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.exception.TicketInvalidException;

import java.util.HashMap;
import java.util.Map;

public class Locker {
    private int freeCapacity;
    private final Map<Ticket, Bag> ticketBagMap = new HashMap<>();

    public Locker(int freeCapacity) {
        this.freeCapacity = freeCapacity;
    }

    public Locker() {

    }

    public Ticket save(Bag bag) {
        if (!isNotFull()) {
            throw new FullCapacityException("储物柜已满");
        }

        Ticket ticket = new Ticket();
        ticketBagMap.put(ticket, bag);
        freeCapacity--;
        return ticket;
    }

    public Bag take(Ticket ticket) {
        if (!isExist(ticket)) {
            throw new TicketInvalidException("无效票据");
        }

        Bag bag = ticketBagMap.get(ticket);
        ticketBagMap.remove(ticket);
        freeCapacity++;
        return bag;
    }

    public boolean isNotFull() {
        return freeCapacity > 0;
    }

    public boolean isExist(Ticket ticket) {
        return ticketBagMap.containsKey(ticket);
    }
}
