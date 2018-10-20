package com.degloba.domain.event;

import com.degloba.event.api.IEvent;
import com.degloba.event.api.IEventBus;

/**
 * Event bus interface. Event Publisher to create an event object, then the object as a parameter to the event call post () method, the event is published to the event bus;
 * Method to register by calling the register () event subscriber to the event bus, the event subscriber to provide one or more areas of a certain type of event
 * When the only parameter, and markedSubscribe the way, when the bus in the event there is a new type of event publishing and the matching parameter of the method, the event
 * Bus will be the event object as an argument to call the event a subscriber subscription method.
 */
public interface IDomainEventBus<T extends IEvent> extends IEventBus<T>{

    /**
     * The field event to all registered event subscriber
     * @param event To publish an event
     */
    void publishEvent(ADomainEvent event);

    /**
     * Register an event subscription method all subscribers registered to receive events to the event bus.
     * @param subscriber Event subscribers. Event subscriber must implement one or more instances of a sub-class of a field event as the only parameter
     * And labeled asSubscribe approach.
     */
    void registerSubscriber(Object subscriber);

    /**
     * Deregistration a registered events to the event bus subscribers all subscription methods.
     * @param subscriber Event subscribers.
     */
    void unregisterSubscriber(Object subscriber);
}
