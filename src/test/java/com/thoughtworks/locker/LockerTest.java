package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.exception.TicketInvalidException;
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
        Locker locker = new Locker(1);
        locker.save(new Bag());

        locker.save(new Bag());
    }

    @Test
    public void should_take_bag_successfully_when_take_bag_given_locker_with_10_capacity_and_valid_ticket() {
        Locker locker = new Locker(10);
        Bag bag = new Bag();
        Ticket ticket = locker.save(bag);

        Bag takenBag = locker.take(ticket);

        assertEquals(bag, takenBag);
    }

    @Test(expected = TicketInvalidException.class)
    public void should_throw_ticket_is_illegal_exception_when_take_bag_given_locker_and_illegal_ticket() {
        Locker locker = new Locker(10);

        locker.take(new Ticket());

    }

    @Test(expected = TicketInvalidException.class)
    public void should_throw_ticket_invalid_exception_when_take_bag_given_locker_and_reused_ticket() {
        Locker locker = new Locker(1);
        Bag bag = new Bag();
        Ticket ticket = locker.save(bag);
        locker.take(ticket);

        locker.take(ticket);
    }

    @Test
    public void should_return_ticket_when_save_bag_given_a_full_locker_then_take_bag_and_new_bag() {
        Locker locker = new Locker(1);
        Ticket ticket = locker.save(new Bag());
        locker.take(ticket);
        Bag bag = new Bag();

        Ticket otherTicket = locker.save(bag);

        assertNotNull(otherTicket);
    }
}
