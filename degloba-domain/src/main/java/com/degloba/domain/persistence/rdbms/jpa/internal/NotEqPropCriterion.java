package com.degloba.domain.persistence.rdbms.jpa.internal;

/**
 * On behalf of a property is not equal to another attribute query criteria
 */
public class NotEqPropCriterion extends PropertyCompareCriterion {

    /**
     * Create a query condition
     * @param propName Property name
     * @param otherPropName Another attribute name
     */
    public NotEqPropCriterion(String propName, String otherPropName) {
        super(propName, otherPropName);
        setOperator(" != ");
    }
}
