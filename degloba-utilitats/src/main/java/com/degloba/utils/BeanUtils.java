package com.degloba.utils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JavaBean class tools.
 */
public class BeanUtils {

    private final Object bean;
    private final BeanClassUtils beanClassUtils;

    public BeanUtils(final Object bean) {
        //Assert.notNull(bean);
        this.bean = bean;
        beanClassUtils = new BeanClassUtils(bean.getClass());
    }

    /**
     * Bean replication between two Property Value
     *
     * @param fromBean As the copy source Bean
     * @param toBean   Bean as a replication target
     */
    private static void copyProperties(Object fromBean, Object toBean, String... excludeProps) {
        BeanUtils from = new BeanUtils(fromBean);
        BeanUtils to = new BeanUtils(toBean);
        Map<String, Object> values = from.getPropValues();
        Set<String> propsToCopy = to.getWritablePropNames();
        if (excludeProps != null) {
            propsToCopy.removeAll(Arrays.asList(excludeProps));
        }
        for (String prop : propsToCopy) {
            if (values.containsKey(prop)) {
                to.setPropValue(prop, values.get(prop));
            }
        }
    }

    /**
     * Types Get all the attributes specified JavaBean type, including inherited from the parent class properties
     *
     * @return A Map, Key for the Property name, Value Class belongs to the property
     */
    public Map<String, Class<?>> getPropTypes() {
        return beanClassUtils.getPropTypes();
    }

    /**
     * JavaBean get all the Property Value, including inherited from the parent class properties
     *
     * @return A Map, which Key to Property name, Value of Property Value.
     */
    public Map<String, Object> getPropValues() {
        return getPropValues(getReadablePropNames());
    }

    /**
     * Get the JavaBean Property Value values, including property inherited from a parent class, it does not contain the specified property.
     *
     * @param excludePropNames To exclude Property name
     * @return A Map, which Key to Property name, Value of Property Value.
     */
    public Map<String, Object> getPropValuesExclude(String... excludePropNames) {
        return getPropValues(beanClassUtils.getReadablePropNamesExclude(excludePropNames));
    }

    /**
     * Get the JavaBean Property Value values, including property inherited from a parent class, it does not contain the specified property.
     *
     * @param excludeAnnotations Shipment Annotation, Annotation indexed these attributes will be excluded.
     * @return A Map, which Key to Property name, Value of Property Value.
     */
    public Map<String, Object> getPropValuesExclude(Class<? extends Annotation>... excludeAnnotations) {
        return getPropValues(beanClassUtils.getReadablePropNamesExclude(excludeAnnotations));
    }

    private Map<String, Object> getPropValues(Set<String> propNames) {
        Map<String, Object> results = new HashMap<String, Object>();
        Map<String, PropertyDescriptor> props = beanClassUtils.getPropertyDescriptors();
        try {
            for (String propName : propNames) {
                PropertyDescriptor propertyDescriptor = props.get(propName);
                Method readMethod = propertyDescriptor.getReadMethod();
                if (readMethod == null) {
                    continue;
                }
                Object value = readMethod.invoke(bean, new Object[]{});
                results.put(propName, value);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    /**
     * Get the names of all the properties of the specified JavaBean type, including inherited from the parent class property
     *
     * @return Property name of the JavaBean collection
     */
    public Set<String> getPropNames() {
        return beanClassUtils.getPropNames();
    }

    /**
     * Get the names of all the properties of the specified JavaBean readable type, including inherited from the parent class property
     *
     * @return Property name of the JavaBean collection
     */
    public Set<String> getReadablePropNames() {
        return beanClassUtils.getReadablePropNames();
    }

    /**
     * Get the names of all the properties of the specified JavaBean readable type, including inherited from the parent class property
     *
     * @return Property name of the JavaBean collection
     */
    public Set<String> getWritablePropNames() {
        return beanClassUtils.getWritablePropNames();
    }

    /**
     * The value for the specified attribute
     *
     * @param propName Property name
     * @return  Property Value
     */
    public Object getPropValue(String propName) {
        return getPropValues().get(propName);
    }

    /**
     * Set up Property Value
     *
     * @param key   To set the value of the Property name
     * @param value Value to be set
     */
    public void setPropValue(String key, Object value) {
        for (Map.Entry<String, PropertyDescriptor> entry : beanClassUtils.getPropertyDescriptors().entrySet()) {
            if (!entry.getKey().equals(key)) {
                continue;
            }
            PropertyDescriptor propertyDescriptor = entry.getValue();
            Method writeMethod = propertyDescriptor.getWriteMethod();
            if (writeMethod == null) {
                continue;
            }
            try {
                writeMethod.invoke(bean, value);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(BeanUtils.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(BeanUtils.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(BeanUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Property Value from filling properties
     *
     * @param properties Property Value represents a group of the Map, Key for the Property name, Value of Property Value
     */
    public void populate(Map<String, ? extends Object> properties) {
        for (Map.Entry<String, ? extends Object> entry : properties.entrySet()) {
            setPropValue(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Property Value extracted from another Bean, Bean fill current properties of the same name
     *
     * @param otherBean Another JavaBean
     */
    public void copyPropertiesFrom(Object otherBean) {
        copyProperties(otherBean, bean);
    }

    /**
     * Property Value of the current Bean Bean filled another property of the same name
     *
     * @param otherBean Another JavaBean
     */
    public void copyPropertiesTo(Object otherBean) {
        copyProperties(bean, otherBean);
    }

    /**
     * Property Value extracted from another Bean, Bean fill current properties of the same name
     *
     * @param otherBean    Another JavaBean
     * @param excludeProps Property name does not participate in replication
     */
    public void copyPropertiesFrom(Object otherBean, String... excludeProps) {
        copyProperties(otherBean, bean, excludeProps);
    }

    /**
     * Property Value of the current Bean Bean filled another property of the same name
     *
     * @param otherBean    Another JavaBean
     * @param excludeProps Property name does not participate in replication
     */
    public void copyPropertiesTo(Object otherBean, String... excludeProps) {
        copyProperties(bean, otherBean, excludeProps);
    }
}
