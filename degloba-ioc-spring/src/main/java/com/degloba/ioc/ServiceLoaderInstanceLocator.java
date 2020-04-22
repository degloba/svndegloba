package com.degloba.ioc;

import com.degloba.ioc.interfaces.IInstanceLocator;
import com.degloba.ioc.sharedkernel.exceptions.IocInstanceNotUniqueException;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.ServiceLoader;
import java.util.Set;

/**
 * @author degloba
 * 
 * @category Cercador que utilitza un proveidor IOC (Spring, Guice,..) per cercar beans
 */
@Slf4j
public class ServiceLoaderInstanceLocator implements IInstanceLocator {

    public <T> T getInstance(Class<T> beanType) {
        Set<T> results = new HashSet<T>();
        for (T instance : ServiceLoader.load(beanType)) {
            results.add(instance);
        }
        if (results.size() > 1) {
            throw new IocInstanceNotUniqueException("There're more than one bean of type '" + beanType + "'");
        }
        if (results.size() == 1) {
            return results.iterator().next();
        }
        log.warn("ServiceLoader cannot found bean of type {}", beanType);
        return null;
    }

    public <T> T getInstance(Class<T> beanType, String beanName) {
        Set<T> results = new HashSet<T>();
        for (T instance : ServiceLoader.load(beanType)) {
            Named named = instance.getClass().getAnnotation(Named.class);
            if (named != null && beanName.equals(named.value())) {
                results.add(instance);
            }
        }
        if (results.size() > 1) {
            throw new IocInstanceNotUniqueException("There're more than one bean of type '"
                    + beanType + "' and named '" + beanName + "'");
        }
        if (results.size() == 1) {
            return results.iterator().next();
        }
        log.warn("ServiceLoader cannot found bean '{}' of type {}", beanName, beanType);
        return null;
    }

    public <T> T getInstance(Class<T> beanType, Class<? extends Annotation> annotationType) {
        Set<T> results = new HashSet<T>();
        for (T instance : ServiceLoader.load(beanType)) {
            Annotation beanAnnotation = instance.getClass().getAnnotation(annotationType);
            if (beanAnnotation != null && beanAnnotation.annotationType().equals(annotationType)) {
                results.add(instance);
            }
        }
        if (results.size() > 1) {
            throw new IocInstanceNotUniqueException("There're more than one bean of type '"
                    + beanType + "' and annotated with '" + annotationType + "'");
        }
        if (results.size() == 1) {
            return results.iterator().next();
        }
        log.warn("ServiceLoader cannot found bean of type {} with annotation {}", beanType, annotationType);
        return null;
    }
}
