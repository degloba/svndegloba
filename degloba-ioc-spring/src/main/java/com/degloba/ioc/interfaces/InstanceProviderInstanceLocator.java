package com.degloba.ioc.interfaces;

import com.degloba.events.bus.impl.EventBus;
import com.degloba.ioc.sharedkernel.exceptions.IocInstanceNotFoundException;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;

/**
 * @author degloba
 * 
 * @category Proveidor d'instàncies (Spring o qualsevol framework que sigui capaç de subministrar instàncies)
 */
@Slf4j
public class InstanceProviderInstanceLocator implements IInstanceLocator {

    private IInstanceProvider instanceProvider;

    public InstanceProviderInstanceLocator(IInstanceProvider instanceProvider) {
        this.instanceProvider = instanceProvider;
    }

    public <T> T getInstance(Class<T> beanType) {
        try {
            return instanceProvider.getInstance(beanType);
        } catch (IocInstanceNotFoundException e) {
            log.warn("InstanceProvider cannot found bean of type {}", beanType);
            return null;
        }
    }

    public <T> T getInstance(Class<T> beanType, String beanName) {
        try {
            return instanceProvider.getInstance(beanType, beanName);
        } catch (IocInstanceNotFoundException e) {
            log.warn("InstanceProvider cannot found bean '{}' of type {}", beanName, beanType);
            return null;
        }
    }

    public <T> T getInstance(Class<T> beanType, Class<? extends Annotation> annotationType) {
        try {
            return instanceProvider.getInstance(beanType, annotationType);
        } catch (IocInstanceNotFoundException e) {
            log.warn("InstanceProvider cannot found bean of type {} with annotation {}", beanType, annotationType);
            return null;
        }
    }
}
