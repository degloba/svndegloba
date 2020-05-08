package com.degloba.domain.event;


import com.degloba.domain.events.DomainEvent;
import com.degloba.domain.events.DomainEventBusImpl;
import com.degloba.domain.events.IStoredDomainEventRepository;
import com.degloba.events.api.IEvent;
import com.google.common.eventbus.EventBus;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @category prova el funcionament del DomainEventBus (publicació event, registre, un-registre)
 * 
 * @author degloba
 *
 * @param <T>
 */
public class DomainEventBusImplTest<T extends IEvent> {

    private DomainEventBusImpl<T> instance;

    private EventBus eventBus;

    private IStoredDomainEventRepository eventStore;

    @BeforeEach
    public void setUp() {
        eventBus = mock(EventBus.class);
        eventStore = mock(IStoredDomainEventRepository.class);
        instance = new DomainEventBusImpl<T>(eventBus, eventStore);
    }

    @Test
    public void publishEvent() {
/*        DomainEvent event = new DomainEvent();
        instance.publicaEvent(event);
        verify(eventBus).post(event);
        verify(eventStore).append(event);*/
    }

    @Test
    public void registerSubscriber() {
        Object subscriber = new String("abc");
        instance.registreSubscriber(subscriber);
        verify(eventBus).register(subscriber);
    }

    @Test
    public void unregisterSubscriber() {
        Object subscriber = new String("abc");
        instance.unregisterSubscriber(subscriber);
        verify(eventBus).unregister(subscriber);
    }
}
