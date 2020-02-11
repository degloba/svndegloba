package com.degloba.events.handlers;

import java.lang.reflect.Method;

import org.springframework.beans.factory.BeanFactory;

import com.degloba.events.api.IEvent;

/**
 * @category EventHandler assíncron de tipus {@link IEvent} que hereda de {@link SpringEventHandler} 
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
	public AsynchronousEventHandler(Class<?> eventType, String beanName, Method method, BeanFactory beanFactory) {
		super(eventType, beanName, method, beanFactory);
	}
	
}
