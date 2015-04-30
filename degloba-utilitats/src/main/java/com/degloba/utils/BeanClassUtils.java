package com.degloba.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * JavaBean Class of tools for.
 */
public class BeanClassUtils {

    private final Class<?> clazz;

    /**
     * Takes a class, generate instances BeanClassUtils
     *
     * @param clazz Original class
     */
    public BeanClassUtils(final Class<?> clazz) {
        //Assert.notNull(clazz);
        this.clazz = clazz;
    }

    /**
     * Access to all types of property, including property inherited from the parent class
     *
     * @return A Map, Key for the Property name, Value Class belongs to the property
     */
    public Map<String, Class<?>> getPropTypes() {
        Map<String, Class<?>> results = new HashMap<String, Class<?>>();
        for (Map.Entry<String, PropertyDescriptor> each : getPropertyDescriptors().entrySet()) {
            results.put(each.getKey(), each.getValue().getPropertyType());
        }
        return results;
    }

    /**
     * Get the names of all the properties of the specified JavaBean type, including inherited from the parent class property
     *
     * @return Property name of the JavaBean collection
     */
    public Set<String> getPropNames() {
        return getPropertyDescriptors().keySet();
    }

    /**
     * Get the names of all the properties of the specified JavaBean readable type, including inherited from the parent class property
     *
     * @return Property name of the JavaBean collection
     */
    public Set<String> getReadablePropNames() {
        Set<String> results = new HashSet<String>();
        for (Map.Entry<String, PropertyDescriptor> each : getPropertyDescriptors().entrySet()) {
            if (each.getValue().getReadMethod() == null) {
                continue;
            }
            results.add(each.getKey());
        }
        return results;
    }

    /**
     * Get all writable JavaBean property name specified type, including inherited from the parent class properties
     *
     * @return Property name of the JavaBean collection
     */
    public Set<String> getWritablePropNames() {
        Set<String> results = new HashSet<String>();
        for (Map.Entry<String, PropertyDescriptor> each : getPropertyDescriptors().entrySet()) {
            if (each.getValue().getWriteMethod() == null) {
                continue;
            }
            results.add(each.getKey());
        }
        return results;
    }

    /**
     * Get the JavaBean Property Value values, including property inherited from a parent class, it does not contain the specified property.
     *
     * @param excludePropNames To exclude Property name
     * @return A Map, which Key to Property name, Value of Property Value.
     */
    public Set<String> getReadablePropNamesExclude(String... excludePropNames) {
        List<String> propNamesExclude = Arrays.asList(excludePropNames);
        Set<String> results = new HashSet<String>();
        for (String propName : getReadablePropNames()) {
            if (propNamesExclude.contains(propName)) {
                continue;
            }
            results.add(propName);
        }
        return results;
    }

    /**
     * Get the JavaBean Property Value values, including property inherited from a parent class, it does not contain the property designated by the specified Annotation labeled.
     *
     * @param excludeAnnotations Shipment Annotation, Annotation indexed these attributes will be excluded
     * @return A Map, which Key to Property name, Value of Property Value.
     */
    public Set<String> getReadablePropNamesExclude(Class<? extends Annotation>... excludeAnnotations) {
        List<Class<? extends Annotation>> annotationsExclude = Arrays.asList(excludeAnnotations);
        Set<String> results = new HashSet<String>();
        Map<String, PropertyDescriptor> props = getPropertyDescriptors();
        for (String propName : getReadablePropNames()) {
            PropertyDescriptor propertyDescriptor = props.get(propName);
            Method readMethod = propertyDescriptor.getReadMethod();
            if (readMethod == null) {
                continue;
            }
            if (methodContainsAnnotation(readMethod, annotationsExclude)) {
                continue;
            }
            results.add(propName);
        }
        return results;
    }

    /**
     * Property Description obtain class
     *
     * @return Property described in Class Map, Key to Property name, Value for the property describes the object
     */
    Map<String, PropertyDescriptor> getPropertyDescriptors() {
        Map<String, PropertyDescriptor> results = new HashMap<String, PropertyDescriptor>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
                results.put(propertyDescriptor.getName(), propertyDescriptor);
            }
            results.remove("class");
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    private boolean methodContainsAnnotation(Method readMethod, List<Class<? extends Annotation>> annotationsExclude) {
        for (Class<? extends Annotation> annotationClass : annotationsExclude) {
            if (readMethod.isAnnotationPresent(annotationClass)) {
                return true;
            }
        }
        return false;
    }

}
