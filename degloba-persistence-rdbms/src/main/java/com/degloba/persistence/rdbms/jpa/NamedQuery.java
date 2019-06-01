package com.degloba.persistence.rdbms.jpa;

import com.degloba.persistence.rdbms.jpa.BaseQuery;
import com.degloba.persistence.rdbms.jpa.IEntityRepository;
import com.degloba.utils.Assert;

import java.util.List;

import javax.persistence.NamedNativeQuery;

/**
 * Basat en @NamedNativeQuery.</br> 
 * One of the four inquiries form DDDLib support.
 * You can specify the location query parameters or named query parameters can also be taken for a subset of the query results.
 */
public class NamedQuery extends BaseQuery<NamedQuery> {

    private final String queryName;

    /**
     * Crea un {@link NamedQuery} utilitzant el nom d'un repositori i el nom de @NamedNativeQuery
     * @param repository Repositori
     * @param queryName nom de @NamedNativeQuery
     */
    public NamedQuery(IEntityRepository repository, String queryName) {
        super(repository);
        Assert.notBlank(queryName);
        this.queryName = queryName;
    }

    /**
     * Retorna el nom de @NamedNativeQuery
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
     * Retorna singleQuery results.
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
