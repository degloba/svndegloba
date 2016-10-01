package com.degloba.domain.persistence.rdbms.jpa;

import java.util.List;

import com.degloba.domain.BaseQuery;
import com.degloba.domain.persistence.rdbms.jpa.IEntityRepository;
import com.degloba.utils.Assert;

/**
 * Query string-based object query language. One of the four inquiries form DDDLib support.
 * You can specify the location query parameters or named query parameters can also be taken for a subset of the query results.
 */
public class JpqlQuery extends BaseQuery<JpqlQuery> {
    private final String jpql;

    /**
     * Use Warehousing and JPQL statement creates JPQL query.
     * @param repository Warehousing
     * @param jpql JPQLQuery
     */
    public JpqlQuery(IEntityRepository repository, String jpql) {
        super(repository);
        Assert.notBlank(jpql);
        this.jpql = jpql;
    }

	/**
     * GetJPQLQuery
     * @return JPQLQuery
     */
    public String getJpql() {
        return jpql;
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
