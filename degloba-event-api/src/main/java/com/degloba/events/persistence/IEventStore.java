package com.degloba.event.persistence;

import com.degloba.event.api.IEvent;

/**
 * @category Magatzem d'events {@link IEvent}
 * 
 * @author degloba
 */
public interface IEventStore<T extends IEvent> {

	/**
	 *  Guarda un {@link IEvent}
	 *  
	 * @param event
	 */
    void guarda(T event);
}
