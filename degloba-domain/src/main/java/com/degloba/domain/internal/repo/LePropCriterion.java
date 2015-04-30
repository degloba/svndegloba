package com.degloba.domain.internal.repo;

/**
 * On behalf of a property is less than or equal to another attribute query criteria
 */
public class LePropCriterion extends PropertyCompareCriterion {

    /**
     * Create a query condition
     * @param propName Property name
     * @param otherPropName Another attribute name
     */
    public LePropCriterion(String propName, String otherPropName) {
        super(propName, otherPropName);
        setOperator(" <= ");
    }

}
