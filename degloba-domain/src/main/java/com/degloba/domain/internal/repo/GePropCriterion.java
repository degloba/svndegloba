package com.degloba.domain.internal.repo;

/**
 * The query on behalf of a property is greater than or equal to another attribute
 * @author degloba
 */
public class GePropCriterion extends PropertyCompareCriterion {

    /**
     * Create a query condition
     * @param propName Property name
     * @param otherPropName Another attribute name
     */
    public GePropCriterion(String propName, String otherPropName) {
        super(propName, otherPropName);
        setOperator(" >= ");
    }
}
