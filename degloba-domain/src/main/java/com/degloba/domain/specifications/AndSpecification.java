package com.degloba.domain.specifications;

import com.degloba.utils.Assert;

/**
 * AND specification for creating a new norm, as a result of the other two norms "and (AND)" operations.
 *
 * @param <T> Type parameter that indicates specification applied to the target object type
 */
public class AndSpecification<T> extends AbstractSpecification<T> {

    private final Specification<T> spec1;
    private final Specification<T> spec2;

    /**
     * Based on the other two specifications created them AND norms.
     *
     * @param spec1 The first specification.
     * @param spec2 The second specification.
     */
    public AndSpecification(final Specification<T> spec1, final Specification<T> spec2) {
        Assert.notNull(spec1, "Specification " + spec1 + " is null!");
        Assert.notNull(spec2, "Specification " + spec2 + " is null!");
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    /**
     * {@inheritDoc}
     *
     * @param t To be used to determine whether the object of the present specification AND meet.
     * @return If the object t meet the specifications 1 and 2 two standardized specification returns true, 
     * otherwise it returns false.
     */
    @Override
    public boolean isSatisfiedBy(final T t) {
        return spec1.isSatisfiedBy(t) && spec2.isSatisfiedBy(t);
    }
}