package com.degloba.domain.specification;

import org.dayatang.utils.Assert;

/**
 * NOT specification for creating a new norm, as another specification "non-(NOT)" results of operations.
 *
 * @param <T> Type parameter that indicates specification applied to the target object type.
 */
public class NotSpecification<T> extends AbstractSpecification<T> {

    private final Specification<T> spec1;

    /**
     * Another specification to create it based on the corresponding "non-(NOT)" specification.
     *
     * @param spec1 To perform a "non-(NOT)" operations specification.
     */
    public NotSpecification(final Specification<T> spec1) {
        Assert.notNull(spec1, "Specification " + spec1 + " is null!");
        this.spec1 = spec1;
    }

    /**
     * {@inheritDoc}
     *
     * @param t To be used to perform "non-(NOT)" operations specification.
     * @return If t meet the specification t, it returns false, otherwise it returns true.
     */
    @Override
    public boolean isSatisfiedBy(final T t) {
        return !spec1.isSatisfiedBy(t);
    }
}
