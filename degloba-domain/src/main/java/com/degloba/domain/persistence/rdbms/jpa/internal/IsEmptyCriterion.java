package com.degloba.domain.persistence.rdbms.jpa.internal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.degloba.domain.persistence.rdbms.jpa.NamedParameters;


/**
 * Determine whether a set of search criteria empty set of attribute values
 */
public class IsEmptyCriterion extends BasicCriterion {

    public IsEmptyCriterion(String propName) {
        super(propName);
    }

    @Override
    public String toQueryString() {
        return getPropNameWithAlias() + " is empty";
    }

    public NamedParameters getParameters() {
        return NamedParameters.create();
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IsEmptyCriterion)) {
            return false;
        }
        IsEmptyCriterion that = (IsEmptyCriterion) other;
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
        return getPropName() + " is empty";
    }

}
