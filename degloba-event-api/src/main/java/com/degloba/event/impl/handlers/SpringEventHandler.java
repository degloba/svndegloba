package com.degloba.event.impl.handlers;

import java.lang.reflect.Method;

import org.springframework.beans.factory.BeanFactory;

import com.degloba.event.api.IEvent;
import com.degloba.event.api.IEventHandler;

/**
 * Classe : Handler d'events de tipus d'events {@link IEvent} implementat amb Spring
 */
public class SpringEventHandler<T extends IEvent> implements IEventHandler<T> {

    private final Class<?> eventType;
    private final String beanName;
    private final Method method;
    private final BeanFactory beanFactory;

    public SpringEventHandler(Class<?> eventType, String beanName, Method method, BeanFactory beanFactory) {
        this.eventType = eventType;
        this.beanName = beanName;
        this.method = method;
        this.beanFactory = beanFactory;
    }

    public boolean canHandle(T event) {
        return eventType.isAssignableFrom(event.getClass());
    }

    public void handle(T event) {
        Object bean = beanFactory.getBean(beanName);
        try {
            method.invoke(bean, event);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
