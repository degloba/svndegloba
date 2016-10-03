package com.degloba.domain.persistence.nosql.googleDatastore.api.jpa.internal;

/**
 * On behalf of property equal to the specified value of the query
 */
public class EqCriterion extends ValueCompareCriterion {

    public EqCriterion(String propName, Object value) {
        super(propName, value);
        setOperator(" = ");
    }
}
