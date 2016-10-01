package com.degloba.domain.persistence.rdbms.jpa.internal;

/**
 * Records determine whether a property is greater than the specified value set query criteria
 */
public class SizeGtCriterion extends SizeCompareCriterion {

    /**
     * Create a query condition
     * @param propName Property name
     * @param value  Property Value
     */
    public SizeGtCriterion(String propName, int value) {
        super(propName, value);
        setOperator(" > ");
    }
}
