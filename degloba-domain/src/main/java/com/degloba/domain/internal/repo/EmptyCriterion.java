package com.degloba.domain.internal.repo;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import com.degloba.domain.NamedParameters;
import com.degloba.domain.QueryCriterion;

/**
 * "空"条件，什么也不做。为了简化条件之间的运算
 *
 * @author degloba (<a href="mailto:gdyangyu@gmail.com">gdyangyu@gmail.com</a>)
 */
public class EmptyCriterion extends AbstractCriterion {

    EmptyCriterion() {
    }

    @Override
    public QueryCriterion and(QueryCriterion criterion) {
        return criterion;
    }

    @Override
    public QueryCriterion or(QueryCriterion criterion) {
        return criterion;
    }

    @Override
    public QueryCriterion not() {
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
