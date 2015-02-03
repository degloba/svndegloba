package com.degloba.domain.internal.bean;

import com.degloba.domain.InstanceLocator;
import com.degloba.domain.InstanceLocatorFactory;
import com.degloba.domain.InstanceProvider;

import java.util.Map;

/**
 * Created by yyang on 15/1/14.
 */
public class InstanceLocatorFactoryImpl implements InstanceLocatorFactory {
    public InstanceLocator create(InstanceProvider instanceProvider) {
        return new InstanceProviderInstanceLocator(instanceProvider);
    }

    public InstanceLocator createByServiceLoader() {
        return new ServiceLoaderInstanceLocator();
    }

    public InstanceLocator create(Map<Object, Object> instances) {
        return new MapInstanceLocator(instances);
    }
}
