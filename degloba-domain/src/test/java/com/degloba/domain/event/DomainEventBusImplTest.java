package com.degloba.domain.event;

import com.google.common.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class DomainEventBusImplTest {

    private DomainEventBusImpl instance;

    private EventBus eventBus;

    private IStoredEventRepository eventStore;

    @Before
    public void setUp() {
        eventBus = mock(EventBus.class);
        eventStore = mock(IStoredEventRepository.class);
        instance = new DomainEventBusImpl(eventBus, eventStore);
    }

    @Test
    public void publishEvent() {
        DomainEvent event = new DomainEventSub();
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
