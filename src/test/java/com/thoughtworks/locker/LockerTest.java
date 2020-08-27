package com.thoughtworks.locker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LockerTest {
    @Test
    void should_return_ticket_when_save_bag_given_locker_with_10_free_capacity() {
        Locker locker = new Locker(10);
        Bag bag = new Bag();

        Ticket ticket = locker.save(bag);

        assertNotNull(ticket);
    }
}
