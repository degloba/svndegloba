package com.degloba.domain.internal.repo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import com.degloba.domain.NamedParameters;
import org.dayatang.utils.Assert;

/**
 * 判断某个属性的值是否位于指定的值空间范围的查询条件
 * @author degloba
 */
public class BetweenCriterion extends BasicCriterion {

    private final Comparable<?> from;

    private final Comparable<?> to;

    /**
     * Create a query condition实例
     * @param propName Property name称
     * @param from 值的下限
     * @param to 值的上限
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
     * @return 当前对象实例的哈希值
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
