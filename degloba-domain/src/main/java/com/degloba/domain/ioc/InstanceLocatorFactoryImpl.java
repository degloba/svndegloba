package com.degloba.domain.ioc;

import com.degloba.domain.ioc.IInstanceLocator;
import com.degloba.domain.ioc.IInstanceLocatorFactory;
import com.degloba.domain.ioc.IInstanceProvider;

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
