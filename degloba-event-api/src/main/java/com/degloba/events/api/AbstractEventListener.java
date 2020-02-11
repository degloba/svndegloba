package com.degloba.events.api;

import java.lang.reflect.ParameterizedType;

/**
 * @category EventListener de tipus d'events {@link IEvent}
 * 
 * @author degloba
 */
public abstract class AbstractEventListener<T extends IEvent> implements IEventListener<T> {

    @Override
    public void onEvent(T event) {
        if (!supports(event)) {
            return;
        }
        handle((T) event);
    }

    /**
     * @category retorna  @True si la propia instància (es un {@link EventListenr}) suporta l'event
     * que ha rebut
     * 
     * @param event
     * @return
     */
    private boolean supports(T event) {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Class<?> eventClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
        //System.out.println("Event class is: " + eventClass);
        return eventClass.isAssignableFrom(event.getClass());
    }

    public abstract void handle(T event);
}
