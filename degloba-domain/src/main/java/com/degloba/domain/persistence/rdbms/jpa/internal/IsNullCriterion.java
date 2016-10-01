package com.degloba.domain.persistence.rdbms.jpa.internal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import com.degloba.domain.persistence.rdbms.jpa.NamedParameters;

/**
 * Determine if a property value is null query criteria
 */
public class IsNullCriterion extends BasicCriterion {

    public IsNullCriterion(String propName) {
        super(propName);
    }

    @Override
    public String toQueryString() {
        return getPropNameWithAlias() + " is null";
    }

    public NamedParameters getParameters() {
        return NamedParameters.create();
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IsNullCriterion)) {
            return false;
        }
        IsNullCriterion that = (IsNullCriterion) other;
        return new EqualsBuilder()
                .append(this.getPropName(), that.getPropName())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getPropName()).toHashCode();
    }

    @Override
    public String toString() {
        return getPropName() + " is null";
    }

}
