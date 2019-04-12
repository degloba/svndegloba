package com.degloba.event.api;

/**
 * Interficie : Bus d'Events de tipus {@link IEvent}
 */
public interface IEventBus<T extends IEvent> {

    /**
     * @param handlers Per registrar {@link EventListener}S
     */
    void register(IEventListener<T> handlers);

    /**
     * @param handlers Per un-registrar {@link EventListener}S
     */
    void unregister(IEventListener<T> handlers);

    /**
     * @param event Per "post" un {@link IEvent}
     */
    void post(T event);
}
