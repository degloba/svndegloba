package com.degloba.ecommerce.sales.application.events.guava.eventbus.events;

public class SimpleEvent {

    private String name;

    public SimpleEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}