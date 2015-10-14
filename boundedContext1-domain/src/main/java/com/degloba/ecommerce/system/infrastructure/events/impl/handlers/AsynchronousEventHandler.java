package com.degloba.ecommerce.system.infrastructure.events.impl.handlers;

import java.lang.reflect.Method;

import org.springframework.beans.factory.BeanFactory;

/**
 * TODO this is just a fake impl, based on standard synchronous impl
 * 
 * @author degloba
 *
 */
public class AsynchronousEventHandler extends SpringEventHandler{

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
