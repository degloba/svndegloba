package com.degloba.event.bus;

import com.degloba.event.api.IEvent;
import com.degloba.event.api.IEventListener;

/**
 * @category Bus d'events de tipus {@link IEvent}
 * 
 * @author degloba
 */
public interface IEventBus<T extends IEvent> {

    /**
     * @param eventlistener Per registrar {@link EventListener}S
     */
    void register(IEventListener<T> eventlistener);

    /**
     * @param eventlistener Per un-registrar {@link EventListener}S
     */
    void unregister(IEventListener<T> eventlistener);

    /**
     * @param event Per "post" un {@link IEvent}
     */
    void post(T event);
}
