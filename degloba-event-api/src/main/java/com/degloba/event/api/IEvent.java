package com.degloba.event.api;

import java.util.Date;

/**
 * Interfície : Event
 */
public interface IEvent {
	 
	String id();

    Date occurredOn();

    int version();
}
