package com.degloba.event.api;

import com.google.common.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;


public abstract class AbstractEventListener<T extends IEvent> implements IEventListener<T> {

    @Override
    public void onEvent(T event) {
        if (!supports(event)) {
            return;
        }
        handle((T) event);
    }

    //
    private boolean supports(IEvent event) {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Class<?> eventClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
        //System.out.println("Event class is: " + eventClass);
        return eventClass.isAssignableFrom(event.getClass());
    }

    public abstract void handle(T event);
}
