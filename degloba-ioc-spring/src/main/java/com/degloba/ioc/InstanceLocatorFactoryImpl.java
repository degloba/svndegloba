package com.degloba.ioc;

import java.util.Map;

import com.degloba.ioc.InstanceProviderInstanceLocator;
import com.degloba.ioc.MapInstanceLocator;
import com.degloba.ioc.ServiceLoaderInstanceLocator;
import com.degloba.ioc.IInstanceLocator;
import com.degloba.ioc.IInstanceLocatorFactory;
import com.degloba.ioc.IInstanceProvider;

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
