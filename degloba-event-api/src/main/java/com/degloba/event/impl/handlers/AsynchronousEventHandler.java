package com.degloba.event.impl.handlers;

import java.lang.reflect.Method;

import org.springframework.beans.factory.BeanFactory;

import com.degloba.event.api.IEvent;

/**
 * TODO this is just a fake impl, based on standard synchronous impl
 * 
 * @author degloba
 *
 */
public class AsynchronousEventHandler<T extends IEvent> extends SpringEventHandler<T>{

	/**
	 * @param eventType
	 * @param beanName
	 * @param method
	 * @param beanFactory
	 */
	public AsynchronousEventHandler(Class<?> eventType, String beanName,
			Method method, BeanFactory beanFactory) {
		super(eventType, beanName, method, beanFactory);
	}
	
}
