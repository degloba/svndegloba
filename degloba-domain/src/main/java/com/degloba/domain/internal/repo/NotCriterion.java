package com.degloba.domain.internal.repo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import com.degloba.domain.NamedParameters;
import com.degloba.domain.QueryCriterion;
import org.dayatang.utils.Assert;

/**
 * On behalf of a query condition negated query conditions
 * @author degloba
 */
public class NotCriterion extends AbstractCriterion {

    private final QueryCriterion criterion;

    /**
     * Create it negated query conditions based on a query condition
     * @param criterion The original search criteria
     */
    public NotCriterion(QueryCriterion criterion) {
        Assert.notNull(criterion, "Query criterion is null!");
        this.criterion = criterion;
    }

    /**
     * Return to the original query condition
     * @return The original search criteria
     */
    public QueryCriterion getCriteron() {
        return criterion;
    }

    @Override
    public String toQueryString() {
        return "not (" + criterion.toQueryString() + ")";
    }

    public NamedParameters getParameters() {
        return criterion.getParameters();
    }

    /**
     * Equivalence of judgment
     * @param other To be used to convict like another object
     * @return If the current object and other equivalent returns true, false otherwise
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NotCriterion)) {
            return false;
        }
        NotCriterion that = (NotCriterion) other;
        return new EqualsBuilder()
                .append(this.getCriteron(), that.getCriteron())
                .isEquals();
    }

    /**
     * Calculate the hash value
     * @return The hash value of the object instance
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getCriteron()).toHashCode();
    }

}
