package com.degloba.ioc.spring.locators;

import java.lang.annotation.Annotation;

/**
 * @author degloba
 * 
 * @category Cercador d'instancies java<br/><br/>
 * 
 * <b>NO vinculat a cap proveidor (Spring,...)</b>
 * 
 */
public interface IInstanceLocator {

    public abstract <T> T getInstance(Class<T> beanType);

    public abstract <T> T getInstance(Class<T> beanType, String beanName);

    public abstract <T> T getInstance(Class<T> beanType, Class<? extends Annotation> annotationType);
}
