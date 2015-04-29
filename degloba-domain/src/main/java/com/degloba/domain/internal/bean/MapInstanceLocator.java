package com.degloba.domain.internal.bean;

import com.degloba.domain.InstanceLocator;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public class MapInstanceLocator implements InstanceLocator {

    private Map<Object, Object> instances = new HashMap<Object, Object>();

    public MapInstanceLocator(Map<Object, Object> instances) {
        this.instances = instances;
    }

    @SuppressWarnings("unchecked")
	public <T> T getInstance(Class<T> beanType) {
        return (T) instances.get(beanType);
    }

    @SuppressWarnings("unchecked")
	public <T> T getInstance(Class<T> beanType, String beanName) {
        return (T) instances.get(toName(beanType, beanName));
    }

    @SuppressWarnings("unchecked")
	public <T> T getInstance(Class<T> beanType, Class<? extends Annotation> annotationType) {
        return (T) instances.get(toName(beanType, annotationType));
    }

    private static String toName(Class<?> beanType, String beanName) {
        return beanType.getName() + ":" + beanName;
    }

    private static String toName(Class<?> beanType, Class<? extends Annotation> annotationType) {
        return beanType.getName() + ":" + annotationType.getName();
    }
}
