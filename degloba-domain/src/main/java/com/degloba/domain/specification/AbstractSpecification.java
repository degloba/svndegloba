package com.degloba.domain.specification;

/**
 * 抽象规范实现，实现了规范的“与”、“或”、“非”操作。
 *
 * @param <T> 泛型参数
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