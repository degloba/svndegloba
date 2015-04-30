package com.degloba.domain.internal.repo;

/**
 * Representative attribute is less than the specified value query condition
 */
public class LtCriterion extends ValueCompareCriterion {

	public LtCriterion(String propName, Comparable<?> value) {
        super(propName, value);
        setOperator(" < ");
    }
}
