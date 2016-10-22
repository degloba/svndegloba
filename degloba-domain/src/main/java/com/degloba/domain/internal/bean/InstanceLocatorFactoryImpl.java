package com.degloba.domain.internal.bean;

import com.degloba.domain.IInstanceLocator;
import com.degloba.domain.IInstanceLocatorFactory;
import com.degloba.domain.IInstanceProvider;

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
