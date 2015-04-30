package com.degloba.domain.internal.repo;

/**
 * On behalf of property equal to the specified value of the query
 */
public class EqCriterion extends ValueCompareCriterion {

    public EqCriterion(String propName, Object value) {
        super(propName, value);
        setOperator(" = ");
    }
}
