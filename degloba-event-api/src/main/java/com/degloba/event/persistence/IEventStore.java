package com.degloba.event.persistence;

import com.degloba.event.api.IEvent;

/**
 * @category Interfície : Magatzem d'events
 */
public interface IEventStore<T extends IEvent> {

	/**
	 *  Guarda un {@link IEvent}
	 *  
	 * @param event
	 */
    void store(T event);
}
