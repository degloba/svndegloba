package com.degloba.domain.persistence.nosql.googleDatastore.api.jpa.internal;

/**
 * On behalf of property is not equal to the specified value of the query
 */
public class NotEqCriterion extends ValueCompareCriterion {

    public NotEqCriterion(String propName, Object value) {
        super(propName, value);
        setOperator(" <> ");
    }
}
