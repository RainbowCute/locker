package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.exception.TicketInvalidException;

import java.util.HashMap;
import java.util.Map;

public class Locker implements Storable{
    private final int capacity;
    private final Map<Ticket, Bag> ticketBagMap = new HashMap<>();

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket save(Bag bag) {
        if (isFull()) {
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

    @Override
    public boolean isFull() {
        return ticketBagMap.size() >= capacity;
    }

    @Override
    public boolean isExist(Ticket ticket) {
        return ticketBagMap.containsKey(ticket);
    }

    public int getFreeCapacity() {
        return capacity - ticketBagMap.size();
    }

    public int getCapacity() {
        return capacity;
    }
}
