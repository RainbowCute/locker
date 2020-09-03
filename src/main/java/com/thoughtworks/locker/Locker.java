package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.exception.TicketInvalidException;

import java.util.HashMap;
import java.util.Map;

public class Locker {
    private final int capacity;
    private final Map<Ticket, Bag> ticketBagMap = new HashMap<>();

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket save(Bag bag) {
        if (!isNotFull()) {
            throw new FullCapacityException();
        }

        Ticket ticket = new Ticket();
        ticketBagMap.put(ticket, bag);
        return ticket;
    }

    public Bag take(Ticket ticket) {
        if (!isExist(ticket)) {
            throw new TicketInvalidException();
        }

        Bag bag = ticketBagMap.get(ticket);
        ticketBagMap.remove(ticket);
        return bag;
    }

    public boolean isNotFull() {
        return ticketBagMap.size() < capacity;
    }

    public boolean isExist(Ticket ticket) {
        return ticketBagMap.containsKey(ticket);
    }

    public int getFreeCapacity() {
        return capacity - ticketBagMap.size();
    }
}
