package com.degloba.viatges.vendes.eventsourcing.events;

public class NoSubscriberEvent {

    String message = "I'm lost";

    public String getMessage() {
        return message;
    }
}