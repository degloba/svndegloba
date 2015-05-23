package com.degloba.persistence.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.degloba.domain.BaseQuery;
import com.degloba.domain.CriteriaQuery;
import com.degloba.domain.Entity;
import com.degloba.domain.EntityRepository;
import com.degloba.domain.ExampleSettings;
import com.degloba.domain.InstanceFactory;
import com.degloba.domain.JpqlQuery;
import com.degloba.domain.NamedParameters;
import com.degloba.domain.NamedQuery;
import com.degloba.domain.PositionalParameters;
import com.degloba.domain.QueryParameters;
import com.degloba.domain.SqlQuery;
import com.google.appengine.api.datastore.Key;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * EntityRepositoryJpa by EntityManagerProviderGetEntityManager, to ensure that the current thread and transaction
 * 
 * Multiple access to the database are performed by the same EntityManager.
 * 
 * In order to obtain the corresponding JPQL named query based on the name, we need to provide a NamedQueryParser. 
 * JPA specification does not enforce this requirement,
 * Depending on the JPA implementation, to configure the NamedQueryParser achieve IoC container.
 */
@Repository
@Transactional
public class EntityRepositoryJpa implements com.degloba.domain.EntityRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityRepositoryJpa.class);

    //Named query parser, it is optional
    private NamedQueryParser namedQueryParser;
    
    private EntityManagerProvider entityManagerProvider;

    
    @PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactory")
    protected EntityManager entityManager;
    
	EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }
    
    public EntityRepositoryJpa() {
        entityManagerProvider = new EntityManagerProvider();
    }

    public EntityRepositoryJpa(EntityManager entityManager) {
        entityManagerProvider = new EntityManagerProvider(entityManager);
    }

    public EntityRepositoryJpa(EntityManagerFactory entityManagerFactory) {
        entityManagerProvider = new EntityManagerProvider(entityManagerFactory);
    }

    public EntityRepositoryJpa(NamedQueryParser namedQueryParser, EntityManagerFactory entityManagerFactory) {
        this(entityManagerFactory);
        setNamedQueryParser(namedQueryParser);
    }

    private NamedQueryParser getNamedQueryParser() {
        if (namedQueryParser == null) {
            namedQueryParser = InstanceFactory.getInstance(NamedQueryParser.class);
        }
        namedQueryParser.setEntityManagerProvider(entityManagerProvider);
        return namedQueryParser;
    }

    public final void setNamedQueryParser(NamedQueryParser namedQueryParser) {
        namedQueryParser.setEntityManagerProvider(entityManagerProvider);
		this.namedQueryParser = namedQueryParser;
	}



    @Override
    public <T extends com.degloba.domain.Entity> T save(T entity) {
        if (entity.notExisted()) {
        	getEntityManager().persist(entity);
        	getEntityManager().flush();
            LOGGER.info("create a entity: " + entity.getClass() + "/"
                    + entity.getId() + ".");
            return entity;
        }
        T result = getEntityManager().merge(entity);
        LOGGER.info("update a entity: " + entity.getClass() + "/"
                + entity.getId() + ".");
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.degloba.domain.EntityRepository#remove(com.degloba.domain.Entity)
     */
    @Override
    public void remove(com.degloba.domain.Entity entity) {
    	getEntityManager().remove(get(entity.getClass(), entity.getId()));
        LOGGER.info("remove a entity: " + entity.getClass() + "/"
                + entity.getId() + ".");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.degloba.domain.EntityRepository#exists(java.io.Serializable)
     */
    @Override
    public <T extends com.degloba.domain.Entity> boolean exists(final Class<T> clazz,
                                             final Key id) {
        T entity = getEntityManager().find(clazz, id);
        return entity != null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.degloba.domain.EntityRepository#get(java.io.Serializable)
     */
/*    @Override
    public <T extends Entity> T get(final Class<T> clazz, final Serializable id) {
        return entityManager.find(clazz, id);
    }*/
    @Override
    public <T extends com.degloba.domain.Entity> T get(final Class<T> clazz, final Key id) {
        return getEntityManager().find(clazz, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.degloba.domain.EntityRepository#load(java.io.Serializable)
     */
    @Override
    public <T extends com.degloba.domain.Entity> T load(final Class<T> clazz, final Serializable id) {
        return getEntityManager().getReference(clazz, id);
    }

    @Override
    public <T extends com.degloba.domain.Entity> T getUnmodified(final Class<T> clazz,
                                              final T entity) {
    	getEntityManager().detach(entity);
        return get(clazz, entity.getId());
    }

    @Override
    public <T extends com.degloba.domain.Entity> T getByBusinessKeys(Class<T> clazz, NamedParameters keyValues) {
        List<T> results = findByProperties(clazz, keyValues);
        return results.isEmpty() ? null : results.get(0);
    }

    @SuppressWarnings("unchecked")
	@Override
    public <T extends com.degloba.domain.Entity> List<T> findAll(final Class<T> clazz) {
        String queryString = "select o from " + clazz.getName() + " as o";
        return getEntityManager().createQuery(queryString).getResultList();
    }

    @Override
    public <T extends com.degloba.domain.Entity> CriteriaQuery createCriteriaQuery(Class<T> entityClass) {
        return new CriteriaQuery(this, entityClass);
    }

    @SuppressWarnings("unchecked")
	@Override
    public <T> List<T> find(CriteriaQuery criteriaQuery) {
        Query query = getEntityManager().createQuery(criteriaQuery.getQueryString());
        processQuery(query, criteriaQuery.getParameters(), 
                criteriaQuery.getFirstResult(), criteriaQuery.getMaxResults());
        return query.getResultList();
    }

    @Override
    public <T> T getSingleResult(CriteriaQuery dddQuery) {
        List<T> results = find(dddQuery);
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public JpqlQuery createJpqlQuery(String jpql) {
        return new JpqlQuery(this, jpql);
    }

    @SuppressWarnings("unchecked")
	@Override
    public <T> List<T> find(JpqlQuery jpqlQuery) {
        return getQuery(jpqlQuery).getResultList();
    }

    @SuppressWarnings("unchecked")
	@Override
    public <T> T getSingleResult(JpqlQuery jpqlQuery) {
        try {
            return (T) getQuery(jpqlQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public int executeUpdate(JpqlQuery jpqlQuery) {
        return getQuery(jpqlQuery).executeUpdate();

    }

    private Query getQuery(JpqlQuery jpqlQuery) {
        Query query = getEntityManager().createQuery(jpqlQuery.getJpql());
        processQuery(query, jpqlQuery);
        return query;
    }

    @Override
    public NamedQuery createNamedQuery(String queryName) {
        return new NamedQuery(this, queryName);
    }

    @Override
    public <T> List<T> find(NamedQuery namedQuery) {
        return getQuery(namedQuery).getResultList();
    }

    @SuppressWarnings("unchecked")
	@Override
    public <T> T getSingleResult(NamedQuery namedQuery) {
        try {
            return (T) getQuery(namedQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public int executeUpdate(NamedQuery namedQuery) {
        return getQuery(namedQuery).executeUpdate();
    }

    private Query getQuery(NamedQuery namedQuery) {
        Query query = getEntityManager().createNamedQuery(namedQuery.getQueryName());
        processQuery(query, namedQuery);
        return query;
    }

    @Override
    public SqlQuery createSqlQuery(String sql) {
        return new SqlQuery(this, sql);
    }

    @Override
    public <T> List<T> find(SqlQuery sqlQuery) {
        return getQuery(sqlQuery).getResultList();
    }

    @SuppressWarnings("unchecked")
	@Override
    public <T> T getSingleResult(SqlQuery sqlQuery) {
        try {
            return (T) getQuery(sqlQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public int executeUpdate(SqlQuery sqlQuery) {
        return getQuery(sqlQuery).executeUpdate();
    }

	private Query getQuery(SqlQuery sqlQuery) {
        Query query;
        if (sqlQuery.getResultEntityClass() == null) {
            query = getEntityManager().createNativeQuery(sqlQuery.getSql());
        } else {
            query = getEntityManager().createNativeQuery(sqlQuery.getSql(),
                    sqlQuery.getResultEntityClass());
        }
        processQuery(query, sqlQuery);
        return query;
    }

    @Override
    public <T extends com.degloba.domain.Entity, E extends T> List<T> findByExample(
            final E example, final ExampleSettings<T> settings) {
        throw new RuntimeException("not implemented yet!");
    }

    @Override
    public <T extends com.degloba.domain.Entity> List<T> findByProperty(Class<T> clazz, String propertyName, Object propertyValue) {
        return find(new CriteriaQuery(this, clazz).eq(propertyName, propertyValue));
    }

    @Override
    public <T extends com.degloba.domain.Entity> List<T> findByProperties(Class<T> clazz, NamedParameters properties) {
        CriteriaQuery criteriaQuery = new CriteriaQuery(this, clazz);
        for (Map.Entry<String, Object> each : properties.getParams().entrySet()) {
            criteriaQuery = criteriaQuery.eq(each.getKey(), each.getValue());
        }
        return find(criteriaQuery);
    }

    @Override
    public String getQueryStringOfNamedQuery(String queryName) {
        return getNamedQueryParser().getQueryStringOfNamedQuery(queryName);
    }

    @Override
    public void flush() {
    	getEntityManager().flush();
    }

    @Override
    public void refresh(com.degloba.domain.Entity entity) {
    	getEntityManager().refresh(entity);
    }

    @Override
    public void clear() {
    	getEntityManager().clear();
    }

    private void processQuery(Query query, BaseQuery<?> originQuery) {
        processQuery(query, originQuery.getParameters(), 
                originQuery.getFirstResult(), originQuery.getMaxResults());
    }

    private void processQuery(Query query, QueryParameters parameters, 
            int firstResult, int maxResults) {
        fillParameters(query, parameters);
        query.setFirstResult(firstResult);
        if (maxResults > 0) {
            query.setMaxResults(maxResults);
        }
    }

    private void fillParameters(Query query, QueryParameters params) {
        if (params == null) {
            return;
        }
        if (params instanceof PositionalParameters) {
            fillParameters(query, (PositionalParameters) params);
        } else if (params instanceof NamedParameters) {
            fillParameters(query, (NamedParameters) params);
        } else {
            throw new UnsupportedOperationException("An argument that is not supported");
        }
    }

    private void fillParameters(Query query, PositionalParameters params) {
        Object[] paramArray = params.getParams();
        for (int i = 0; i < paramArray.length; i++) {
            query = query.setParameter(i + 1, paramArray[i]);
        }
    }

    private void fillParameters(Query query, NamedParameters params) {
        for (Map.Entry<String, Object> each : params.getParams().entrySet()) {
            query = query.setParameter(each.getKey(), each.getValue());
        }
    }


}
