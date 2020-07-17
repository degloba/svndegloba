package com.degloba.ioc.spring.factories;

import java.util.Map;

import com.degloba.ioc.spring.locators.IInstanceLocator;
import com.degloba.ioc.spring.providers.IInstanceProvider;


/**
 * @author degloba
 * 
 * @category Cercador d'instancies java
 */
public interface IInstanceLocatorFactory {
	
    IInstanceLocator create(IInstanceProvider instanceProvider);
    IInstanceLocator createByServiceLoader();
    IInstanceLocator create(Map<Object, Object> instances);
}
