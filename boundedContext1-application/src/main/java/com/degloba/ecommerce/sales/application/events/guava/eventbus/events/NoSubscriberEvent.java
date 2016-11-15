package com.degloba.ecommerce.sales.application.events.guava.eventbus.events;

public class NoSubscriberEvent {

    String message = "I'm lost";

    public String getMessage() {
        return message;
    }
}