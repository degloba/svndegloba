package com.degloba.domain.persistence.nosql.googleDatastore.api.jpa.internal;

/**
 * Analyzing a set number of records is less than the specified value attribute query conditions
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
