package com.degloba.ioc.spring;

import java.util.Map;


/**
 * 
 */
public class InstanceLocatorFactoryImpl implements IInstanceLocatorFactory {
    public IInstanceLocator create(IInstanceProvider instanceProvider) {
        return new InstanceProviderInstanceLocator(instanceProvider);
    }

    public IInstanceLocator createByServiceLoader() {
        return new ServiceLoaderInstanceLocator();
    }

    public IInstanceLocator create(Map<Object, Object> instances) {
        return new MapInstanceLocator(instances);
    }
}
