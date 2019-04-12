package com.degloba.persistence.rdbms.jpa.internal;

/**
 * Representative property is greater than the specified value of the query
 */
public class GtCriterion extends ValueCompareCriterion {

    public GtCriterion(String propName, Comparable<?> value) {
        super(propName, value);
        setOperator(" > ");
    }
}
