package com.degloba.infrastructure.events.impl;

import java.lang.reflect.Method;

import javax.inject.Inject;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.degloba.event.annotations.EventListener;
import com.degloba.event.api.IEvent;
import com.degloba.event.api.IEventHandler;
import com.degloba.event.impl.DomainEventPublisher;
import com.degloba.event.impl.handlers.AsynchronousEventHandler;
import com.degloba.event.impl.handlers.SpringEventHandler;

import com.degloba.infrastructure.sagas.impl.SagaInstance;

/**
 * @category Registra mètodes beans Spring com handlers d'events en spring {@link DomainEventPublisher}
 * (if needed).
 */
@Component
public class EventListenerBeanPostProcessor<T extends IEvent> implements BeanPostProcessor, BeanFactoryAware {

    private BeanFactory beanFactory;
    
    @Inject
    private DomainEventPublisher<T> eventPublisher;

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!(bean instanceof SagaInstance)) {
            for (Method method : bean.getClass().getMethods()) {
            	EventListener listenerAnnotation = method.getAnnotation(EventListener.class);            	
                
            	if (listenerAnnotation == null) {
                    continue;
                }
                
            	Class<?> eventType = method.getParameterTypes()[0];
                
                if (listenerAnnotation.asynchronous()){
                	//TODO just a temporary fake impl
                	IEventHandler<T> handler = new AsynchronousEventHandler<T>(eventType, beanName, method, beanFactory);
                	//TODO add to some queue
                	eventPublisher.registerEventHandler(handler);                	
                }
                else{                
                	IEventHandler<T> handler = new SpringEventHandler<T>(eventType, beanName, method, beanFactory);
                	eventPublisher.registerEventHandler(handler);
                }                                
            }
        }
        return bean;
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // do nothing
        return bean;
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        eventPublisher = beanFactory.getBean(DomainEventPublisher.class);
    }
}
