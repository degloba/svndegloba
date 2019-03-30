package com.degloba.domain.event;

import com.degloba.event.api.IEvent;
import com.google.common.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class DomainEventBusImplTest<T extends IEvent> {

    private DomainEventBusImpl<T> instance;

    private EventBus eventBus;

    private IStoredDomainEventRepository eventStore;

    @Before
    public void setUp() {
        eventBus = mock(EventBus.class);
        eventStore = mock(IStoredDomainEventRepository.class);
        instance = new DomainEventBusImpl<T>(eventBus, eventStore);
    }

    @Test
    public void publishEvent() {
        ADomainEvent event = new DomainEventSub();
        instance.publishEvent(event);
        verify(eventBus).post(event);
        verify(eventStore).append(event);
    }

    @Test
    public void registerSubscriber() {
        Object subscriber = new String("abc");
        instance.registerSubscriber(subscriber);
        verify(eventBus).register(subscriber);
    }

    @Test
    public void unregisterSubscriber() {
        Object subscriber = new String("abc");
        instance.unregisterSubscriber(subscriber);
        verify(eventBus).unregister(subscriber);
    }
}
