package com.degloba.domain;

import java.util.Map;

/**
 */
public interface IInstanceLocatorFactory {
    IInstanceLocator create(IInstanceProvider instanceProvider);
    IInstanceLocator createByServiceLoader();
    IInstanceLocator create(Map<Object, Object> instances);
}
