package com.degloba.domain.specifications;

import com.degloba.utils.Assert;

/**
 * NOT specification for creating a new norm, as another specification "non-(NOT)" results of operations.
 *
 * @param <T> Indica el tipus d'objecte al que s'aplica l'especificació.
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
     * @return Si t cumpleix l'especificació, retorna {@code false}, en qualsevol altra cas retorna {@code true}.
     */
    @Override
    public boolean isSatisfiedBy(final T t) {
        return !spec1.isSatisfiedBy(t);
    }
}
