package com.degloba.event.api;


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
