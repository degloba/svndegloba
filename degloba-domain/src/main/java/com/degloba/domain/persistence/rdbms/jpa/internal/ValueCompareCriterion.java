package com.degloba.domain.persistence.rdbms.jpa.internal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import com.degloba.domain.persistence.rdbms.jpa.NamedParameters;
import com.degloba.utils.Assert;
import com.degloba.utils.BeanUtils;

import java.util.Map;

/**
 * Representative Property Value compared to the value of a specified class of query conditions
 */
public abstract class ValueCompareCriterion extends BasicCriterion {
    protected final Object value;

    private String operator;

    public ValueCompareCriterion(String propName, Object value) {
        super(propName);
        Assert.notNull(value, "Value is null!");
        this.value = value;
    }

    /**
     * Get Match value
     *
     * @return  Match value
     */
    public Object getValue() {
        return value;
    }

    protected final void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toQueryString() {
        return getPropNameWithAlias() + operator + getParamNameWithColon();
    }

    public NamedParameters getParameters() {
        return NamedParameters.create().add(getParamName(), value);
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(getClass().isAssignableFrom(other.getClass()))) {
            return false;
        }
        Map<String, Object> thisPropValues = new BeanUtils(this).getPropValues();
        Map<String, Object> otherPropValues = new BeanUtils(other).getPropValues();
        return new EqualsBuilder()
                .append(thisPropValues.get("propName"), otherPropValues.get("propName"))
                .append(thisPropValues.get("value"), otherPropValues.get("value"))
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getPropName())
                .append(getValue())
                .toHashCode();
    }

    @Override
    public String toString() {
        return getPropName() + operator + value;
    }
}
