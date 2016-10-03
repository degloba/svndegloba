package com.degloba.domain.persistence.nosql.googleDatastore.api.jpa.internal;

/**
 * Representative property is greater than the specified value of the query
 */
public class GtCriterion extends ValueCompareCriterion {

    public GtCriterion(String propName, Comparable<?> value) {
        super(propName, value);
        setOperator(" > ");
    }
}
