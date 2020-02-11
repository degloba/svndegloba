package com.degloba.events.bus;

import com.degloba.events.api.AbstractEventListener;
import com.degloba.events.api.IEvent;


/**
 * @category Bus d'events de tipus {@link IEvent}
 * 
 * @author degloba
 */
public interface IEventBus<T extends IEvent> {

	void registra(AbstractEventListener<?> newInstance);


}
