package com.degloba.persistence.rdbms.api.jpa;

import java.util.List;

import com.degloba.persistence.rdbms.api.jpa.IEntityRepository;
import com.degloba.utils.Assert;

/**
 * Query string-based object query language. 
 * Una de les quatre inquiries form DDDLib support.
 * You can specify the location query parameters or named query parameters can also be taken for a subset of the query results.
 */
public class JpqlQuery extends BaseQuery<JpqlQuery> {
    private final String jpql;

    /**
     * La utilització d'un repositori i d'una instrucció JPQL crea una JPQL query.
     * @param repository Repositori
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
     * Retorna una llista de resultats a partir de la {@link JpqlQuery}.
     * @param <T> Tipus d'element de la llista de resultats de la {@link JpqlQuery}
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
     * @return Número d'entitats modificades o esborrades
     */
    @Override
    public int executeUpdate() {
        return getRepository().executeUpdate(this);
    }
}
