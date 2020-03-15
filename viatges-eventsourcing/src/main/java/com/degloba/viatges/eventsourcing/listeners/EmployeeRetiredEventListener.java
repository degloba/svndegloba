package com.degloba.viatges.eventsourcing.listeners;


import com.degloba.events.api.AbstractEventListener;
import com.degloba.viatges.eventsourcing.events.EmployeeRetiredEvent;

public class EmployeeRetiredEventListener extends AbstractEventListener<EmployeeRetiredEvent> {

    private int count = 0;

    public int getCount() {
        return count;
    }

    @Override
    public void handle(EmployeeRetiredEvent event) {
        count ++;
    }
}