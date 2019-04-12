package com.degloba.ioc;

import java.lang.annotation.Annotation;

/**
 * Bean Examples positioning
 */
public interface IInstanceLocator {

    public abstract  <T> T getInstance(Class<T> beanType);

    public abstract <T> T getInstance(Class<T> beanType, String beanName);

    public abstract <T> T getInstance(Class<T> beanType, Class<? extends Annotation> annotationType);
}
