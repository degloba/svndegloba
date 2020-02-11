package com.degloba.event.api;

/** 
 * @category Listener d'events de tipus {@link IEvent}
 * 
 * @author degloba
 */
public interface IEventListener<T extends IEvent> {

    void onEvent(T event);

}
