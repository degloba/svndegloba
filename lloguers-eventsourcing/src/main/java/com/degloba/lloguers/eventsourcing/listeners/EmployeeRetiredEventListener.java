package com.degloba.lloguers.eventsourcing.listeners;


import com.degloba.events.api.AbstractEventListener;
import com.degloba.lloguers.eventsourcing.events.EmployeeRetiredEvent;

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