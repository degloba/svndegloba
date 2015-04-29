package com.degloba.domain.internal.repo;

/**
 * Analyzing a collection of attributes the number of records is not equal to the specified value of the query
 * @author degloba
 */
public class SizeNotEqCriterion extends SizeCompareCriterion {

    /**
     * Create a query condition
     * @param propName Property name
     * @param value  Property Value
     */
    public SizeNotEqCriterion(String propName, int value) {
        super(propName, value);
        setOperator(" != ");
    }
}
