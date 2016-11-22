package com.degloba.domain.ioc;

import com.degloba.domain.ioc.IInstanceLocator;
import com.degloba.domain.ioc.IInstanceProvider;
import com.degloba.domain.sharedkernel.exceptions.IocInstanceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;

/**
 * 
 */
public class InstanceProviderInstanceLocator implements IInstanceLocator {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstanceProviderInstanceLocator.class);

    private IInstanceProvider instanceProvider;

    public InstanceProviderInstanceLocator(IInstanceProvider instanceProvider) {
        this.instanceProvider = instanceProvider;
    }

    public <T> T getInstance(Class<T> beanType) {
        try {
            return instanceProvider.getInstance(beanType);
        } catch (IocInstanceNotFoundException e) {
            LOGGER.warn("InstanceProvider cannot found bean of type {}", beanType);
            return null;
        }
    }

    public <T> T getInstance(Class<T> beanType, String beanName) {
        try {
            return instanceProvider.getInstance(beanType, beanName);
        } catch (IocInstanceNotFoundException e) {
            LOGGER.warn("InstanceProvider cannot found bean '{}' of type {}", beanName, beanType);
            return null;
        }
    }

    public <T> T getInstance(Class<T> beanType, Class<? extends Annotation> annotationType) {
        try {
            return instanceProvider.getInstance(beanType, annotationType);
        } catch (IocInstanceNotFoundException e) {
            LOGGER.warn("InstanceProvider cannot found bean of type {} with annotation {}", beanType, annotationType);
            return null;
        }
    }
}
