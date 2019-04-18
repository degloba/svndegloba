package com.degloba.persistence.rdbms.jpa;

import com.degloba.persistence.rdbms.jpa.IEntityRepository;
import com.degloba.utils.Assert;

import java.util.List;

/**
 * Consultes basades en SQL natives. 
 * You can specify the location query parameters or named query parameters can also be taken for a subset of the query results.
 */
public class SqlQuery extends BaseQuery<SqlQuery> {

    private final String sql;
    private Class<? extends IEntity> resultEntityClass;

    /**
     * Emmagatzema i utilitza sentències SQL per crear consultes SQL.
     * @param repository Warehousing
     * @param sql SQLQuery
     */
    public SqlQuery(IEntityRepository repository, String sql) {
        super(repository);
        Assert.notBlank(sql);
        this.sql = sql;
    }

    /**
     * Retorn una consulta SQL
     * @return SQLQuery
     */
    public String getSql() {
        return sql;
    }

    /**
     * Retorna els resultats d'una consulta d'un tipus {@link IEntity}. The result is applied to the case of an entity or entities list.
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
     * Retorna una llista de resultats de la consulta.
     * @param <T> Tipus d'element de la llista de resultats de la consulta
     * @return Resultats de la consulta.
     */
    @Override
    public <T> List<T> list() {
        return getRepository().find(this);
    }

    /**
     * Retorna un únic resultat de la consulta.
     * @param <T> Tipus d'element de la llista de resultats de la consulta
     * @return Resultat de la consulta.
     */
    @Override
    public <T> T singleResult() {
        return getRepository().getSingleResult(this);
    }

    /**
     * Executa una modificació
     * @return Nombre d'entitats {@link IEntity} modificades o esborrades
     */
    @Override
    public int executeUpdate() {
        return getRepository().executeUpdate(this);
    }
}
