package com.degloba.infrastructure.events.impl;

// Reflection
import java.lang.reflect.Method;

import javax.inject.Inject;

// Spring
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

// Event (degloba)
import com.degloba.event.annotations.EventListener;
import com.degloba.event.api.IEvent;
import com.degloba.event.impl.SimpleEventPublisher;
import com.degloba.event.impl.handlers.AsynchronousEventHandler;
import com.degloba.event.impl.handlers.IEventHandler;
import com.degloba.event.impl.handlers.SpringEventHandler;


// Sagas
import com.degloba.infrastructure.sagas.impl.SagaInstance;

/**
 * Registers spring beans methods as event handlers in spring event publisher
 * (if needed).
 */
@Component
public class EventListenerBeanPostProcessor<T extends IEvent> implements BeanPostProcessor, BeanFactoryAware {

    private BeanFactory beanFactory;
    
    @Inject
    private SimpleEventPublisher<T> eventPublisher;

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
                	IEventHandler<T> handler = new AsynchronousEventHandler(eventType, beanName, method, beanFactory);
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
        eventPublisher = beanFactory.getBean(SimpleEventPublisher.class);
    }
}
