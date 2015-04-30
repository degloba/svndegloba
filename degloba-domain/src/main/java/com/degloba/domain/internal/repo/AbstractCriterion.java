package com.degloba.domain.internal.repo;

import java.util.ArrayList;
import java.util.List;

import com.degloba.domain.QueryCriterion;

/**
 * Query conditions abstract base class that implements the AND, OR, NOT operation.
 */
public abstract class AbstractCriterion implements QueryCriterion {
    
    protected String queryString;

    /**
     * AND operation is performed, the return on behalf of two QueryCriterion "and" Operating Results of a new QueryCriterion
     *
     * @param criterion Another QueryCriterion
     * @return The results of the current object with the criterion "and" action
     */
    public QueryCriterion and(QueryCriterion criterion) {
        return new AndCriterion(this, criterion);
    }

    /**
     * Perform an OR operation, returns on behalf of two QueryCriterion "or" operating results of a new QueryCriterion
     *
     * @param criterion Another QueryCriterion
     * @return The results of the current object and criterion of "or" action
     */
    public QueryCriterion or(QueryCriterion criterion) {
        return new OrCriterion(this, criterion);
    }

    /**
     * Do NOT operation, return on behalf of the current object "not" operation a new QueryCriterion
     * @return Results "not" operation of the current object
     */
    public QueryCriterion not() {
        return new NotCriterion(this);
    }

    /**
     * Determine whether they are "empty" condition object that is an instance EmptyCriterion. 
     * When judged mainly used to generate a query string
     * @return In addition EmptyCriterion subclass returns true, the default return false.
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * Removed from the array to Null or members of EmptyCriterion returns the remaining elements of the list
     * @param criterions Original condition array
     * @return List to remove air condition object after the remaining members of the
     */
    protected List<QueryCriterion> removeNullOrEmptyCriterion(QueryCriterion[] criterions) {
        List<QueryCriterion> results = new ArrayList<QueryCriterion>();
        for (QueryCriterion each : criterions) {
            if (each == null || each.isEmpty()) {
                continue;
            }
            results.add(each);
        }
        return results;
    }

    /**
     * Get the query corresponding query string
     * @return Query String
     */
    public String toQueryString() {
        return queryString;
    }
    
    
    /**
     * Equivalence of judgment
     * @param other To be used to convict like another object
     * @return If the current object and other equivalent returns true, false otherwise
     */
    @Override
    public abstract boolean equals(final Object other);

    /**
     * Calculate the hash value
     * @return The hash value of the object instance
     */
    @Override
    public abstract int hashCode();
}
