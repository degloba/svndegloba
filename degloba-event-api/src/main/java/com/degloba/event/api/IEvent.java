package com.degloba.event.api;

import java.util.Date;

/**
 * Evento
 */
public interface IEvent {

    String id();

    Date occurredOn();

    int version();
}
