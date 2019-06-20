package com.degloba.event.api;

import java.util.Date;

/**
 * @author degloba
 * 
 * @category Event
 */
public interface IEvent {
	 
	String id();

    Date occurredOn();

    int version();
}
