package com.degloba.event.bus.google.events;

public class SimpleEvent {

    private String name;

    public SimpleEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}