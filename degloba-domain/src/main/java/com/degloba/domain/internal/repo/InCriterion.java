package com.degloba.domain.internal.repo;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.degloba.domain.Entity;
import com.degloba.domain.NamedParameters;

/**
 * Query condition values represent a property contained in the specified collection or array
 *
 * @author degloba
 */
public class InCriterion extends BasicCriterion {

    private Collection<? extends Object> value = new ArrayList<Object>();

    /**
     * Create a query condition
     * @param propName Property name
     * @param value Collection value
     */
    public InCriterion(String propName, Collection<? extends Object> value) {
        super(propName);
        if (value != null) {
            this.value = value;
        }
    }

    /**
     * Create a query condition
     * @param propName Property name
     * @param value An array of values
     */
    public InCriterion(String propName, Object[] value) {
        super(propName);
        if (value != null && value.length > 0) {
            this.value = Arrays.asList(value);
        }
    }

    /**
     * Get Collection value
     * @return Collection value
     */
    public Collection<? extends Object> getValue() {
        return value;
    }

    @Override
    public String toQueryString() {
        if (value == null || value.isEmpty()) {
            return "1 > 1";
        }
        return getPropNameWithAlias() + " in " + getParamNameWithColon();
    }

    public NamedParameters getParameters() {
        NamedParameters result = NamedParameters.create();
        if (!value.isEmpty()) {
            result = result.add(getParamName(), value);
        }
        return result;
    }

    private String createInString(Collection<? extends Object> value) {
        Set<Object> elements = new HashSet<Object>();
        for (Object item : value) {
            Object element;
            if (item instanceof Entity) {
                element = ((Entity) item).getId();
            } else {
                element = item;
            }
            if (element instanceof String || element instanceof Date) {
                element = "'" + element + "'";
            }
            elements.add(element);
        }
        return StringUtils.join(elements, ", ");
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InCriterion)) {
            return false;
        }
        InCriterion that = (InCriterion) other;
        return new EqualsBuilder()
                .append(this.getPropName(), that.getPropName())
                .append(value, that.value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getPropName()).append(value).toHashCode();
    }

    @Override
    public String toString() {
        return getPropName() + " in collection [" + collectionToString(value) + "]";
    }

    private String collectionToString(Collection<? extends Object> value) {
        return StringUtils.join(value, ", ");
    }

}
