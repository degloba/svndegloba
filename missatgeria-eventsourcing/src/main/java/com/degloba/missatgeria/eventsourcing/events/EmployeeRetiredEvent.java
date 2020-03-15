package com.degloba.missatgeria.eventsourcing.events;

import java.util.Date;

import com.degloba.events.api.AbstractEvent;

public class EmployeeRetiredEvent extends AbstractEvent {
    public EmployeeRetiredEvent() {
    }

    public EmployeeRetiredEvent(Date occurredOn) {
        super(occurredOn);
    }

    public EmployeeRetiredEvent(Date occurredOn, int version) {
        super(occurredOn, version);
    }


}