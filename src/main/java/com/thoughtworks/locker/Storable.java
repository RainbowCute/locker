package com.thoughtworks.locker;

public interface Storable {

    Ticket save(Bag bag);

    Bag take(Ticket ticket);

    boolean isFull();

    boolean isExist(Ticket ticket);

}
