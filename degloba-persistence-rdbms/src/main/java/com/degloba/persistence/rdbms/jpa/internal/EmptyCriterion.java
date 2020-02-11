package com.degloba.persistence.rdbms.jpa.internal;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.degloba.persistence.rdbms.jpa.NamedParameters;
import com.degloba.persistence.rdbms.jpa.IQueryCriterion;

/**
 * "Empty" conditions, do nothing. To simplify operation between conditions
 */
public class EmptyCriterion extends AbstractCriterion {

    EmptyCriterion() {
    }

    @Override
    public IQueryCriterion and(IQueryCriterion criterion) {
        return criterion;
    }

    @Override
    public IQueryCriterion or(IQueryCriterion criterion) {
        return criterion;
    }

    @Override
    public IQueryCriterion not() {
        return this;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public String toQueryString() {
        return "";
    }

    public NamedParameters getParameters() {
        return NamedParameters.create();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EmptyCriterion)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).toHashCode();
    }

}
