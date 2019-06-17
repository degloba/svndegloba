package com.degloba.event.persistence;

import com.degloba.event.api.IEvent;

/**
 * 
 * @author degloba
 * 
 * @category Magatzem d'events de domini
 */
public interface IStoredDomainEvent  extends IEventStore<IEvent> {

}
