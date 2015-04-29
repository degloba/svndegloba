package com.degloba.domain.internal.repo;

/**
 * On behalf of a property is greater than another attribute query criteria
 * @author degloba
 */
public class GtPropCriterion extends PropertyCompareCriterion {

    /**
     * Create a query condition
     * @param propName Property name
     * @param otherPropName Another attribute name
     */
    public GtPropCriterion(String propName, String otherPropName) {
        super(propName, otherPropName);
        setOperator(" > ");
    }
}
