package com.degloba.domain.internal.repo;

/**
 * Representative property is less than or equal to the specified value of the query
 */
public class LeCriterion extends ValueCompareCriterion {

    public LeCriterion(String propName, Comparable<?> value) {
        super(propName, value);
        setOperator(" <= ");
    }
}
