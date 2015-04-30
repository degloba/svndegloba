package com.degloba.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Array tools
 */
public class ArrayUtils {

    private ArrayUtils() {
    }

    /**
     * Extracting an array of Property Value of each element to form a new array.
     *
     * @param items    Original array
     * @param property To extract the array element property
     * @return Array specified by the value of a property of the original composition of each element in the array
     */
    public static Object[] substract(Object[] items, String property) {
        Assert.notNull(items);
        Assert.notBlank(property, "property name must not empty!");
        if (items.length == 0) {
            return new Object[0];
        }
        Object[] results = new Object[items.length];
        for (int i = 0; i < items.length; i++) {
            Object item = items[i];
            Map<String, Object> propValues = new BeanUtils(item).getPropValues();
            if (!propValues.containsKey(property)) {
                throw new IllegalArgumentException("Property " + property + " not exists!");
            }
            results[i] = propValues.get(property);
        }
        return results;
    }

    /**
     * Extracting an attribute of each element in the array forming the new array, 
     * and then forming a string with a specified separator connected.
     *
     * @param items    Original array
     * @param property  To extract the array element property
     * @param separator String delimiter
     * @return A string value of the specified property from the original array for each element specified delimiter connecting formation.
     */
    public static String join(Object[] items, String property, String separator) {
        if (items == null || items.length == 0) {
            return "";
        }
        return StringUtils.join(substract(items, property), separator);
    }
}
