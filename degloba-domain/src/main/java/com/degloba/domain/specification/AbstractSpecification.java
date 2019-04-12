package com.degloba.domain.specification;

/**
 * Abstract norms to achieve, achieve the standard, "and," "or," "not" operation.
 *
 * @param <T> Generic parameters
 */
public abstract class AbstractSpecification<T> implements com.degloba.domain.specification.Specification<T> {

    /**
     * {@inheritDoc}
     */
    public Specification<T> and(final Specification<T> specification) {
        return new AndSpecification<T>(this, specification);
    }

    /**
     * {@inheritDoc}
     */
    public Specification<T> or(final Specification<T> specification) {
        return new OrSpecification<T>(this, specification);
    }

    /**
     * {@inheritDoc}
     */
    public Specification<T> not() {
        return new NotSpecification<T>(this);
    }

	public boolean isSatisfiedBy(T t) {
		// TODO Auto-generated method stub
		return false;
	}
}
