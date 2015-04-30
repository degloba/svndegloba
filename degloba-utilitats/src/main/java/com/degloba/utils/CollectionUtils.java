package com.degloba.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Tool set
 */
public class CollectionUtils {

    private CollectionUtils() {
        super();
    }

    /**
     * Extracting a set of attributes for each element of the formation of a new collection.
     *
     * @param items    The original collection
     * @param property To extract a set of element attributes
     * @return The new collection from the original value of a specified set of attributes for each element consisting of
     */
    public static Collection<?> substract(Collection<?> items, String property) {
        if (items == null) {
            return null;
        }
        if (StringUtils.isEmpty(property)) {
            throw new IllegalArgumentException("property name must not empty!");
        }
        Collection<Object> results = new ArrayList<Object>();
        for (Object item : items) {
            Map<String, Object> propValues = new BeanUtils(item).getPropValues();
            if (!propValues.containsKey(property)) {
                throw new IllegalArgumentException("Property " + property + " not exists!");
            }
            results.add(propValues.get(property));
        }
        return results;
    }

    /**
     * Extracting a set of attributes for each element of the formation of a new collection, 
     * and then linked together to form a string with the specified delimiter.
     *
     * @param items     The original collection
     * @param property  To extract a set of element attributes
     * @param separator String delimiter
     * @return A string that specifies the attributes for each element of the original set of values for the connection specified delimiter up to form.
     */
    public static String join(Collection<?> items, String property, String separator) {
        if (items == null) {
            return "";
        }
        return StringUtils.join(substract(items, property), separator);
    }
}
