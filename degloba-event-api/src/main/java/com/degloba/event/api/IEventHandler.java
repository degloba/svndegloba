package com.degloba.event.api;

/**
 * @category Interfície :  Handler d'events de tipus {@link IEvent}
 */
public interface IEventHandler<T extends IEvent> {
    boolean canHandle(T event);

    void handle(T event);
}