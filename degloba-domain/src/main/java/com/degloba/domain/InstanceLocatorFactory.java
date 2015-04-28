package com.degloba.domain;

import java.util.Map;

/**
 */
public interface InstanceLocatorFactory {
    InstanceLocator create(InstanceProvider instanceProvider);
    InstanceLocator createByServiceLoader();
    InstanceLocator create(Map<Object, Object> instances);
}
