package com.degloba.domain.persistence.rdbms.jpa.internal;

/**
 * Analyzing a collection of attributes the number of records is less than or equal to the specified value of the query
 */
public class SizeLeCriterion extends SizeCompareCriterion {

    /**
     * Create a query condition
     * @param propName Property name
     * @param value  Property Value
     */
    public SizeLeCriterion(String propName, int value) {
        super(propName, value);
        setOperator(" <= ");
    }
}
