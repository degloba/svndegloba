package com.degloba.domain.persistence.nosql.googleDatastore.api.jpa.internal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.degloba.domain.persistence.nosql.googleDatastore.api.jpa.NamedParameters;
import com.degloba.utils.Assert;

/**
 * Determine whether the value of a property located in a specified range of values of spatial query criteria
 */
public class BetweenCriterion extends BasicCriterion {

    private final Comparable<?> from;

    private final Comparable<?> to;

    /**
     * Create a query condition Examples
     * @param propName Property name Say
     * @param from The lower limit value
     * @param to The upper limit value
     */
    public BetweenCriterion(String propName, Comparable<?> from, Comparable<?> to) {
        super(propName);
        Assert.notNull(from, "From value is null!");
        Assert.notNull(to, "To value is null!");
        this.from = from;
        this.to = to;
    }

    /**
     * Get Property Value上限
     * @return  Property Value上限
     */
    public Comparable<?> getFrom() {
        return from;
    }

    /**
     * Get Property Value下限
     * @return  Property Value下限
     */
    public Comparable<?> getTo() {
        return to;
    }

    @Override
    public String toQueryString() {
        return String.format("%s between %s and %s", 
                getPropNameWithAlias(),
                getParamNameWithColon() + "_from",
                getParamNameWithColon() + "_to");
    }

    public NamedParameters getParameters() {
        return NamedParameters.create()
                .add(getParamName() + "_from", from)
                .add(getParamName() + "_to", to);
    }

    /**
     * Equivalence of judgment
     * @param other 要用来判等的另一个对象
     * @return If the current object and other equivalent returns true, false otherwise
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BetweenCriterion)) {
            return false;
        }
        BetweenCriterion that = (BetweenCriterion) other;
        return new EqualsBuilder()
                .append(this.getPropName(), that.getPropName())
                .append(this.getFrom(), that.getFrom())
                .append(this.getTo(), that.getTo())
                .isEquals();
    }

    /**
     * Calculate the hash value
     * @return The hash value of the object instance
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getPropName())
                .append(from).append(to).toHashCode();
    }

    @Override
    public String toString() {
        return getPropName() + " between " + from + " and " + to;
    }

}
