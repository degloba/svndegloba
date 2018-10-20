package com.degloba.event.impl.handlers;

import com.degloba.event.api.IEvent;

public interface IEventHandler<T extends IEvent> {
    boolean canHandle(T event);

    void handle(T event);
}