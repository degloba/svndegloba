package com.degloba.domain.specification;

import org.dayatang.utils.Assert;

/**
 * OR specification for creating a new norm, as the other two canonical "or (OR)" results of operations.
 *
 * @param <T> Type parameter that indicates specification applied to the target object type.
 */
public class OrSpecification<T> extends AbstractSpecification<T> {

    private final Specification<T> spec1;
    private final Specification<T> spec2;

    /**
     * Based on the other two specifications created them OR specifications.
     *
     * @param spec1 The first specification.
     * @param spec2 The second specification.
     */
    public OrSpecification(final Specification<T> spec1, final Specification<T> spec2) {
        Assert.notNull(spec1, "Specification " + spec1 + " is null!");
        Assert.notNull(spec2, "Specification " + spec2 + " is null!");
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    /**
     * {@inheritDoc}
     *
     * @param t To be used to determine whether to meet the specifications of the OR object.
     * @return If the object specification 1 and specification to meet at least one of the two specifications 2 returns true, 
     * otherwise it returns false.
     */
    @Override
    public boolean isSatisfiedBy(final T t) {
        return spec1.isSatisfiedBy(t) || spec2.isSatisfiedBy(t);
    }
}
