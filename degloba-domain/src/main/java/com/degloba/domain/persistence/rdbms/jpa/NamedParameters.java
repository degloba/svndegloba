package com.degloba.domain.persistence.rdbms.jpa;

import com.degloba.utils.Assert;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Naming the current query language or parameter set of a query. JPA, Hibernate and SQL are all support positioning
 * Parameters (such as "? ... Where e.name =") and named parameters (such as "... where name =: name") forms. <br>
 * As far as possible in the form of named parameters, positional parameters are behind the form.
 */
public class NamedParameters implements QueryParameters {
    private Map<String, Object> params = new HashMap<String, Object>();
    
    /**
     * Create an empty set of query parameters
     * @return Map-based set of query parameters
     */
    public static NamedParameters create() {
        return new NamedParameters(new HashMap<String, Object>());
    }
    
    /**
     * Create a set of query parameters
     * @param params Map query parameters to be set, Key for the Parameter name, Value for the parameter values
     * @return Map-based set of query parameters
     */
    public static NamedParameters create(Map<String, Object> params) {
        return new NamedParameters(params);
    }

    private NamedParameters(Map<String, Object> params) {
        Assert.notNull(params, "Parameters cannot be null");
        this.params = new HashMap<String, Object>(params);
    }
    
    /**
     * Add a named parameter
     * @param key Parameter name Adding said
     * @param value Parameter Value
     * @return The current object itself
     */
    public NamedParameters add(String key, Object value) {
        Assert.notBlank(key);
        Assert.notNull(value);
        params.put(key, value);
        return this;
    }

    /**
     * The merger came another NamedParameters.
     * @param other The set of parameters to be merged
     * @return The object itself. 
     * Its parameter set is the result of the original parameter set and another set of parameters and after
     */
    public NamedParameters add(NamedParameters other) {
        Assert.notNull(other);
        params.putAll(other.getParams());
        return this;
    }

    /**
     * Obtain parameters Map
     * @return Parameters Map
     */
    public Map<String, Object> getParams() {
        return Collections.unmodifiableMap(params);
    }

    /**
     * Get the object hash
     * @return The hash value of the object
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 43).append(params).toHashCode();
    }

    /**
     * Analyzing parameters set object Equivalence. 
     * If and only if the argument phase two NamedParameters Map contains the same time, the two objects is equivalent.
     * @param other Another object
     * @return If the current object is equivalent to the other returns true, otherwise it returns false.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NamedParameters)) {
            return false;
        }
        NamedParameters that = (NamedParameters) other;
        
        return new EqualsBuilder().append(this.getParams(), that.getParams()).isEquals();
    }

    /**
     * String parameter set obtained representation
     * @return The current string representation of the object
     */
    @Override
    public String toString() {
        return params.toString();
    }
    
}
