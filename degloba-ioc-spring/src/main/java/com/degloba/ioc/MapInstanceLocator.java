package com.degloba.ioc;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import com.degloba.ioc.interfaces.IInstanceLocator;

/**
 * @author degloba
 * 
 * @category Encapsula un {@link Map} d'instàncies java permetent recuperar una instància a partir  
 * 
 * <ul>
 * <li>del seu Tipus</li>
 * <li>del seu Nom</li>
 * <li>d'una {@link Anotation}</li>
 * </ul>
 */
public class MapInstanceLocator implements IInstanceLocator {

    private Map<Object, Object> instances = new HashMap<Object, Object>();

    /**
     * @category Encapsula un {@link Map} d'instàncies java
     * 
     * @param instances
     */
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
