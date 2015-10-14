package com.degloba.ecommerce.system.infrastructure.events.impl.handlers;

/**
 * @author degloba
 */
public interface EventHandler {
    boolean canHandle(Object event);

    void handle(Object event);
}