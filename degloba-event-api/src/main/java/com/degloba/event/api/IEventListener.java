package com.degloba.event.api;

/**
 * @author degloba
 * 
 * @category Listener d'events de tipus {@link IEvent}
 */
public interface IEventListener<T extends IEvent> {

    void onEvent(T event);

}
