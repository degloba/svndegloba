package com.degloba.event.impl.handlers;

public interface IEventHandler {
    boolean canHandle(Object event);

    void handle(Object event);
}