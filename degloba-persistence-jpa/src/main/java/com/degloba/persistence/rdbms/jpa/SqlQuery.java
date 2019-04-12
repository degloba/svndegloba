package com.degloba.persistence.rdbms.jpa;

import com.degloba.persistence.rdbms.jpa.IEntityRepository;
import com.degloba.utils.Assert;

import java.util.List;

/**
 * Native SQL-based queries. One of the four inquiries form DDDLib support.
 * You can specify the location query parameters or named query parameters can also be taken for a subset of the query results.
 */
public class SqlQuery extends BaseQuery<SqlQuery> {

    private final String sql;
    private Class<? extends IEntity> resultEntityClass;

    /**
     * Storage and use SQL statements to create SQL queries.
     * @param repository Warehousing
     * @param sql SQLQuery
     */
    public SqlQuery(IEntityRepository repository, String sql) {
        super(repository);
        Assert.notBlank(sql);
        this.sql = sql;
    }

    /**
     * Get SQL query
     * @return SQLQuery
     */
    public String getSql() {
        return sql;
    }

    /**
     * Return query results entity type. The result is applied to the case of an entity or entities list.
     * @return Entity type (if the result is a collection that is the type of the collection element) query results
     */
    public Class<? extends IEntity> getResultEntityClass() {
        return resultEntityClass;
    }

    /**
     * Set the entity type of the result of the query. Note setResultEntityClass () and addScalar () are mutually exclusive,
     * Were applied to the query results are entities and scalar two cases
     * @param resultEntityClass To set the type of query results
     * @return The object itself
     */
    public SqlQuery setResultEntityClass(Class<? extends IEntity> resultEntityClass) {
        this.resultEntityClass = resultEntityClass;
        return this;
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
