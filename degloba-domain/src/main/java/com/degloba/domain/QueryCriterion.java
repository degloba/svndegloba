package com.degloba.domain;


/**
 * The query interface
 */
public interface QueryCriterion {

    static final String ROOT_ALIAS = "rootEntity";

    /**
     * AND operation is performed, the return on behalf of two QueryCriterion "and" Operating Results of a new QueryCriterion
     *
     * @param criterion Another QueryCriterion
     * @return The results of the current object with the criterion of "and" action
     */
    QueryCriterion and(QueryCriterion criterion);

    /**
     * Perform OR operation, return on behalf of two QueryCriterion "or" operating results of a new QueryCriterion
     *
     * @param criterion Another QueryCriterion
     * @return The results of the current object with the criterion of "or" action
     */
    QueryCriterion or(QueryCriterion criterion);

    /**
     * NOT perform the operation, the return on behalf of the current object "not" operate a new QueryCriterion
     * @return The results "not" current object operations
     */
    QueryCriterion not();

    /**
     * Are empty condition that instance EmptyCriterion of
     * @return If it is empty condition returns true, otherwise false
     */
    boolean isEmpty();

    /**
     * Convert string JPQL
     * @return Query String
     */
    String toQueryString();

    /**
     * Get query parameters
     * @return Parameter set of a query
     */
    NamedParameters getParameters();


}
