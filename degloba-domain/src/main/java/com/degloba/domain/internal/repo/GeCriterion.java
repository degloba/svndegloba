package com.degloba.domain.internal.repo;

/**
 * Representative property is less than or equal to the specified value of the query
 */
public class GeCriterion extends ValueCompareCriterion {

    public GeCriterion(String propName, Comparable<?> value) {
        super(propName, value);
        setOperator(" >= ");
    }
}
