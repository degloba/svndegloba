package com.degloba.ioc.spring;

import java.util.Map;


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
