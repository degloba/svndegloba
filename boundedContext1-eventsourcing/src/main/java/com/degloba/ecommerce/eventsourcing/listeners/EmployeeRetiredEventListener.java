package com.degloba.ecommerce.eventsourcing.listeners;


import com.degloba.ecommerce.eventsourcing.events.EmployeeRetiredEvent;
import com.degloba.events.api.AbstractEventListener;

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