package com.degloba.event.api;

import java.util.Date;

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
