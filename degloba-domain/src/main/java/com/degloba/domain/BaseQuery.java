package com.degloba.domain;

import java.util.List;
import java.util.Map;

import org.dayatang.utils.Assert;

/**
 * Query base class for NamedQuery, JpqlQuery and SqlQuery provide common behavior.
 * @author degloba
 * @param <E> Types of queries
 */
public abstract class BaseQuery<E extends BaseQuery<?>> {
    private final EntityRepository repository;
    private QueryParameters parameters = PositionalParameters.create();
    private final NamedParameters mapParameters = NamedParameters.create();
    private int firstResult;
    private int maxResults;

    public BaseQuery(EntityRepository repository) {
        Assert.notNull(repository);
        this.repository = repository;
    }

    /**
     * Get query parameters
     * @return Query parameters
     */
    public QueryParameters getParameters() {
        return parameters;
    }

    /**
     * Set positioning named parameters (array mode)
     * @param parameters Parameters to be set
     * @return The object itself
     */
    @SuppressWarnings("unchecked")
	public E setParameters(Object... parameters) {
        this.parameters = PositionalParameters.create(parameters);
        return (E)this;
    }

    /**
     * Setting positioning parameters (list mode)
     * @param parameters Parameters to be set
     * @return The object itself
     */
    @SuppressWarnings("unchecked")
	public E setParameters(List<Object> parameters) {
        this.parameters = PositionalParameters.create(parameters);
        return (E) this;
    }

    /**
     * Named parameter set (Map form, Key is the parameter name, Value is the parameter value)
     * @param parameters Parameters to be set
     * @return The object itself
     */
    @SuppressWarnings("unchecked")
	public E setParameters(Map<String, Object> parameters) {
        this.parameters = NamedParameters.create(parameters);
        return (E) this;
    }

    /**
     * Add a named parameter, Key is the parameter name, Value is the parameter value.
     * @param key Named parameter name
     * @param value Parameter Value
     * @return The object itself
     */
    @SuppressWarnings("unchecked")
	public E addParameter(String key, Object value) {
        mapParameters.add(key, value);
        this.parameters = mapParameters;
        return (E) this;
    }

    /**
     * Named parameter set (Map form, Key is the parameter name, Value is the parameter value)
     * @param parameters Parameters to be set
     * @return The object itself
     */
    @SuppressWarnings("unchecked")
	public E setParameters(QueryParameters parameters) {
        this.parameters = parameters;
        return (E) this;
    }

    /**
     * For paging query, get firstResult.
     * firstResult Representative start getting data from a subset of records that satisfy the query first firstResult + 1 strips.
     * @return firstResult Setting value,
     */
    public int getFirstResult() {
        return firstResult;
    }

    /**
     * For paging query, set firstResult.
     * firstResult Representative start getting data from a subset of records that satisfy the query first firstResult + 1 strips.
     * @param firstResult FirstResult to set values.
     * @return The object itself
     */
    @SuppressWarnings("unchecked")
	public E setFirstResult(int firstResult) {
        Assert.isTrue(firstResult >= 0);
        this.firstResult = firstResult;
        return (E) this;
    }

    /**
     * For paging query, get maxResults setting.
     * maxResultsGet the largest number of representatives from the results that satisfy the query data records.
     * @return maxResults Settings.
     */
    public int getMaxResults() {
        return maxResults;
    }

    /**
     * For paging query, set the value maxResults of.
     * maxResults Get the largest number of representatives from the results that satisfy the query data records.
     * @param maxResults MaxResults value to be set
     * @return The object itself
     */
    @SuppressWarnings("unchecked")
	public E setMaxResults(int maxResults) {
        Assert.isTrue(maxResults > 0);
        this.maxResults = maxResults;
        return (E) this;
    }

    /**
     * Return query results list.
     * @param <T> Query results list element type
     * @return Query results.
     */
    public abstract <T> List<T> list();

    /**
     * Returns a single query results.
     * @param <T> Type of query results
     * @return Query results.
     */
    public abstract <T> T singleResult();

    /**
     * Warehousing operations to perform the update.
     * @return Number of updated or deleted entities
     */
    public abstract int executeUpdate();

    /**
     * Get Warehousing object.
     * @return Warehousing object
     */
    protected EntityRepository getRepository() {
        return repository;
    }
    
    
}
