package com.degloba.ecommerce.compres.eventsourcing.events;

public class NoSubscriberEvent {

    String message = "I'm lost";

    public String getMessage() {
        return message;
    }
}