package com.degloba.domain.internal.repo;

/**
 * Analyzing a set number of records is less than the specified value attribute query conditions
 * @author degloba
 */
public class SizeLtCriterion extends SizeCompareCriterion {

    /**
     * Create a query condition
     * @param propName Property name
     * @param value  Property Value
     */
    public SizeLtCriterion(String propName, int value) {
        super(propName, value);
        setOperator(" < ");
    }
}
