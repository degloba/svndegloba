package com.degloba.domain.event;

import java.util.Date;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DomainEventTest {

    private ADomainEvent instance;

    @Test
    public void constructor() {
        instance = new SubDomainEvent(null);
        assertThat(instance.getVersion(), is(1));
        long timeDiff = (new Date()).getTime() - ((Date) instance.getOccurredOn()).getTime();
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
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;


        public SubDomainEvent(Date occurredOn) {
            super(occurredOn);
        }

        public SubDomainEvent(Date occurredOn, int version) {
            super(occurredOn, version);
        }
    }

}
