package com.degloba.event.bus;

import com.degloba.event.api.AbstractEventListener;
import com.degloba.event.api.IEvent;
import com.degloba.event.api.IEventListener;

/**
 * @category Bus d'events de tipus {@link IEvent}
 * 
 * @author degloba
 */
public interface IEventBus<T extends IEvent> {

	void register(AbstractEventListener newInstance);


}
