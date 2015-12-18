package com.degloba.domain;

import com.degloba.utils.Assert;

import java.util.List;

/**
 * Based on the query object named query. One of the four inquiries form DDDLib support.
 * You can specify the location query parameters or named query parameters can also be taken for a subset of the query results.
 */
public class NamedQuery extends BaseQuery<NamedQuery> {

    private final String queryName;

    /**
     * Create a named query using the name of warehousing and named queries
     * @param repository Warehousing
     * @param queryName Naming names query
     */
    public NamedQuery(EntityRepository repository, String queryName) {
        super(repository);
        Assert.notBlank(queryName);
        this.queryName = queryName;
    }

    /**
     * Get the name of the named query
     * @return Named Query Name
     */
    public String getQueryName() {
        return queryName;
    }

    /**
     * Return query results list.
     * @param <T> Query results list element type
     * @return Query results.
     */
    @Override
    public <T> List<T> list() {
        return getRepository().find(this);
    }

    /**
     * Returns singleQuery results.
     * @param <T> Type of query results
     * @return Query results.
     */
    @Override
    public <T> T singleResult() {
        return getRepository().getSingleResult(this);
    }

    /**
     * Perform the update warehousing operation.
     * @return Number of updated or deleted entities
     */
    @Override
    public int executeUpdate() {
        return getRepository().executeUpdate(this);
    }

}
