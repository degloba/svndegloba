package com.degloba.domain.persistence.nosql.googleDatastore.api.jpa.internal;

/**
 * Analyzing a set of attribute values equal to a specified number of records the query
 */
public class SizeEqCriterion extends SizeCompareCriterion {

    /**
     * Create a query condition
     * @param propName Property name
     * @param value Property Value
     */
    public SizeEqCriterion(String propName, int value) {
        super(propName, value);
        setOperator(" = ");
     }
}
