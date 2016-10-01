package com.degloba.domain.persistence.rdbms.jpa.internal;

/**
 * On behalf of a property is equal to another attribute query criteria
 */
public class LtPropCriterion extends PropertyCompareCriterion {

    /**
     * Create a query condition
     * @param propName Property name
     * @param otherPropName Another attribute name
     */
    public LtPropCriterion(String propName, String otherPropName) {
        super(propName, otherPropName);
        setOperator(" < ");
    }
}
