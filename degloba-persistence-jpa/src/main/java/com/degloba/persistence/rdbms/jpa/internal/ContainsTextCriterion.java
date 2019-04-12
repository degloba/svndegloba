package com.degloba.persistence.rdbms.jpa.internal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.degloba.persistence.rdbms.jpa.NamedParameters;
import com.degloba.utils.Assert;

/**
 * The value of a property with a judgment whether the query contains the specified text content
 */
public class ContainsTextCriterion extends BasicCriterion {

    private final String value;

    /**
     * Create a query condition
     * @param propName Property name
     * @param value To include substring in property values
     */
    public ContainsTextCriterion(String propName, String value) {
        super(propName);
        Assert.notBlank(propName, "Property name is null or blank!");
        Assert.notBlank(value, "value is null or blank!");
        this.value = value;
    }

    /**
     * Get Match value
     * @return  Match value
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toQueryString() {
        return getPropNameWithAlias() + " like " + getParamNameWithColon();
    }

    public NamedParameters getParameters() {
        return NamedParameters.create().add(getParamName(), value + "%");
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContainsTextCriterion)) {
            return false;
        }
        ContainsTextCriterion that = (ContainsTextCriterion) other;
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
        return getPropName() + " like '*" + value + "*'";
    }

}
