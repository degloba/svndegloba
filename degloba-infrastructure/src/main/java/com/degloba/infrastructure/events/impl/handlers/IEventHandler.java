package com.degloba.infrastructure.events.impl.handlers;

/**
 * @author Rafał Jamróz
 */
public interface IEventHandler {
    boolean canHandle(Object event);

    void handle(Object event);
}