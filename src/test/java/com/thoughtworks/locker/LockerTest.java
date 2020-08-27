package com.thoughtworks.locker;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LockerTest {
    @Test
    public void should_return_ticket_when_save_bag_given_locker_with_10_free_capacity() {
        Locker locker = new Locker(10);
        Bag bag = new Bag();

        Ticket ticket = locker.save(bag);

        assertNotNull(ticket);
    }

    @Test(expected = FullCapacityException.class)
    public void should_throw_capacity_is_full_exception_when_save_bag_given_locker_with_0_free_capacity() {
        Locker locker = new Locker(0);
        Bag bag = new Bag();

        locker.save(bag);
    }

    @Test
    public void should_take_bag_successfully_when_take_bag_given_locker_with_10_capacity_and_valid_ticket() {
        Locker locker = new Locker(10);
        Bag bag = new Bag();
        Ticket ticket = locker.save(bag);

        Bag takenBag = locker.take(ticket);

        assertEquals(bag, takenBag);
    }

    @Test(expected = IllegalTicketException.class)
    public void should_throw_ticket_is_illegal_exception_when_take_bag_given_illegal_ticket() {
        Locker locker = new Locker();

        locker.take(new Ticket());

    }
}
