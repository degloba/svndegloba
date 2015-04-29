package com.degloba.domain.internal.repo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import com.degloba.domain.NamedParameters;

/**
 * Determine whether a collection attribute value is not an empty set of search criteria
 * @author degloba
 */
public class NotEmptyCriterion extends BasicCriterion {

    public NotEmptyCriterion(String propName) {
        super(propName);
    }

    @Override
    public String toQueryString() {
        return getPropNameWithAlias() + " is not empty";
    }

    public NamedParameters getParameters() {
        return NamedParameters.create();
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NotEmptyCriterion)) {
            return false;
        }
        NotEmptyCriterion that = (NotEmptyCriterion) other;
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
        return getPropName() + " is not empty";
    }

}
