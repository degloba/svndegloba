package com.degloba.domain.event;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;


public class DomainEventTest {

    private ADomainEvent instance;

    @Test
    public void constructor() {
        instance = new SubDomainEvent();
        assertThat(instance.getVersion(), is(1));
        long timeDiff = (new Date()).getTime() - instance.getOccurredOn().getTime();
        assertTrue(timeDiff < 1000);
    }

    @Test
    public void constructorTime() {
        Date occurredOn = new Date();
        instance = new SubDomainEvent(occurredOn);
        assertThat(instance.getVersion(), is(1));
        assertThat(instance.getOccurredOn(), is(occurredOn));
    }

    @Test
    public void constructorTimeAndVersion() {
        Date occurredOn = new Date();
        instance = new SubDomainEvent(occurredOn, 2);
        assertThat(instance.getVersion(), is(2));
        assertThat(instance.getOccurredOn(), is(occurredOn));
    }

    private class SubDomainEvent extends ADomainEvent {
        public SubDomainEvent() {
        }

        public SubDomainEvent(Date occurredOn) {
            super(occurredOn);
        }

        public SubDomainEvent(Date occurredOn, int version) {
            super(occurredOn, version);
        }
    }

}
