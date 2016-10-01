package com.degloba.domain.persistence.rdbms;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.degloba.domain.persistence.rdbms.jpa.QueryParameters;

/**
 * Value or named query language localization parameter set of a query. JPA, Hibernate and SQL are all support positioning
 * Parameter (such as "? ... Where e.name =") and named parameters (such as "... where name =: name") forms. <br>
 * As far as possible in the form of named parameters, positional parameters are behind the form.
 */
public class PositionalParameters implements QueryParameters {
    
    private Object[] params;
    
    /**
     * Create an empty set of query parameters
     * @return An array-based query parameter set
     */
    public static PositionalParameters create() {
        return new PositionalParameters(new Object[]{});
    }
    
    /**
     * Create a query parameter set, filled with an array of parameter values
     * @param params Parameter value array
     * @return Senate based on parameters set array
     */
    public static PositionalParameters create(Object... params) {
        return new PositionalParameters(params);
    }
    
    /**
     * Create a set of query parameters, filling parameter values list
     * @param params List of parameter values
     * @return An array based on the parameters set
     */
    public static PositionalParameters create(List<Object> params) {
        return new PositionalParameters(params.toArray());
    }

    private PositionalParameters(Object[] params) {
        if (params == null) {
            this.params = new Object[]{};
        } else {
            this.params = Arrays.copyOf(params, params.length);
        }
    }

    /**
     * Get the parameter value array
     * @return Parameter array
     */
    public Object[] getParams() {
        return Arrays.copyOf(params, params.length);
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
     * If and only if the argument is an array of two PositionalParameters contain the same, the two objects is equivalent.
     * @param other Another object
     * @return If the current object is equivalent to the other returns true, otherwise it returns false.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PositionalParameters)) {
            return false;
        }
        PositionalParameters that = (PositionalParameters) other;
        return new EqualsBuilder().append(this.getParams(), that.getParams()).isEquals();
    }

    /**
     * String parameter set obtained representation
     * @return The current string representation of the object
     */
    @Override
    public String toString() {
        return Arrays.toString(params);
    }
}    