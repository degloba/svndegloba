package com.degloba.ioc;

import java.util.Map;

import com.degloba.ioc.IInstanceLocator;
import com.degloba.ioc.IInstanceProvider;

/**
 */
public interface IInstanceLocatorFactory {
    IInstanceLocator create(IInstanceProvider instanceProvider);
    IInstanceLocator createByServiceLoader();
    IInstanceLocator create(Map<Object, Object> instances);
}
