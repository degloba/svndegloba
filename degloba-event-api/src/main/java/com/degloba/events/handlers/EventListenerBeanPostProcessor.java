package com.degloba.events.handlers;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.degloba.events.annotations.EventListener;
import com.degloba.events.api.IEvent;
import com.degloba.events.api.IEventHandler;
import com.degloba.events.publishers.DomainEventPublisher;


//import com.degloba.infrastructure.sagas.impl.SagaInstance;

/**
 * @category Registra mètodes beans com handlers d'events en Spring {@link DomainEventPublisher}.<br>
 * Aquests mètodes són els que estàn annotats amb {@link EventListener}
 * 
 * @author degloba
 */
@Component
public class EventListenerBeanPostProcessor<T extends IEvent> implements BeanPostProcessor, BeanFactoryAware {

    private BeanFactory beanFactory;
    
    @Autowired
    private DomainEventPublisher<T> eventPublisher;

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    	// comprovem que no sigui una Saga
        //if (!(bean instanceof SagaInstance)) {
        	// per reflection recuperem els mètodes del bean
            for (Method method : bean.getClass().getMethods()) {
            	// comprovem si el mètode està anotat amb {@link EventListener}
            	EventListener listenerAnnotation = method.getAnnotation(EventListener.class);            	
                
            	if (listenerAnnotation == null) {
                    continue;
                }
                
            	Class<?> eventType = method.getParameterTypes()[0];
                
            	//
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
        //}
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
