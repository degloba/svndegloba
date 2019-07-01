package com.degloba.event.bus.google.subscribers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.eventbus.EventBus;

/**
 * @category Subscripció d'events (Implementació Google)
 */
public abstract class EventSubscriber<T> {

    protected List<T> events = new ArrayList<T>();

    public List<T> getHandledEvents() {
        return events;
    }


    /**
     * @category registra un {@link EventSubscriber} d'un tipus de classe
     * @param clazz
     * @param eventBus
     * @return
     */
    public static <E extends EventSubscriber<?>> E factory(Class<E> clazz, EventBus eventBus) {
        E subscriber = null;
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            subscriber = (E) constructor.newInstance(new Object[]{});
            eventBus.register(subscriber);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return subscriber;
    }
}
