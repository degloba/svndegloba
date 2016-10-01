package com.degloba.domain.persistence.rdbms.jpa;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

// JPA
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

// Domain


import com.degloba.domain.Entity;

import com.degloba.domain.ExampleSettings;

import com.degloba.domain.persistence.rdbms.jpa.NamedParameters;

import com.degloba.domain.persistence.rdbms.jpa.PositionalParameters;
import com.degloba.domain.persistence.rdbms.jpa.QueryCriterion;



// Google App Engine
import com.google.appengine.api.datastore.Key;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Spring
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * JpaEntityRepository by EntityManagerProviderGetEntityManager, to ensure that the current thread and transaction
 * 
 * Multiple access to the database are performed by the same EntityManager.
 * 
 * In order to obtain the corresponding JPQL named query based on the name, we need to provide a NamedQueryParser. 
 * JPA specification does not enforce this requirement,
 * Depending on the JPA implementation, to configure the NamedQueryParser achieve IoC container.
 */
@Repository
@Transactional
public class EntityRepository<A extends BaseAggregateRoot> implements IEntityRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityRepository.class);
  

	@PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactoryDatastore")
    protected EntityManager entityManager;
	
    
    public EntityRepository() {
        //entityManagerProvider = new EntityManagerProvider();
    }

   
    @Override
    public SqlQuery createSqlQuery(String sql) {
        return new SqlQuery(this, sql);
    }
	

    @Override
    public <T extends Entity, E extends T> List<T> findByExample(
            final E example, final ExampleSettings<T> settings) {
        throw new RuntimeException("not implemented yet!");
    }

    @Override
    public <T extends Entity> List<T> findByProperty(Class<T> clazz, String propertyName, Object propertyValue) {
        return find(new CriteriaQuery(this, clazz).eq(propertyName, propertyValue));
    }

    @Override
    public <T extends Entity> List<T> findByProperties(Class<T> clazz, NamedParameters properties) {
        CriteriaQuery criteriaQuery = new CriteriaQuery(this, clazz);
        for (Map.Entry<String, Object> each : properties.getParams().entrySet()) {
            criteriaQuery = criteriaQuery.eq(each.getKey(), each.getValue());
        }
        return find(criteriaQuery);
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

/*    private NamedQueryParser getNamedQueryParser() {
        if (namedQueryParser == null) {
            namedQueryParser = InstanceFactory.getInstance(NamedQueryParser.class);
        }
        namedQueryParser.setEntityManagerProvider(entityManagerProvider);
        return namedQueryParser;
    }*/


	@Override
	public <T extends Entity> T save(T entity) {
		 if (entity.notExisted()) {
			 entityManager.persist(entity);
	            LOGGER.info("create a entity: " + entity.getClass() + "/"
	                    + entity.getId() + ".");
	            return entity;
	        }
	        T result = entityManager.merge(entity);
	        LOGGER.info("update a entity: " + entity.getClass() + "/"
	                + entity.getId() + ".");
	        return result;
	}


	@Override
	public void remove(Entity entity) {
		entityManager.remove(get(entity.getClass(), entity.getId()));
        LOGGER.info("remove a entity: " + entity.getClass() + "/"
                + entity.getId() + ".");	
	}

	@Override
	public <T extends Entity> boolean exists(Class<T> clazz, Key id) {
		 T entity = entityManager.find(clazz, id);
		 return entity != null;
	}


	@Override
	public <T extends Entity> T get(Class<T> clazz, Key id) {		// 
		return entityManager.find(clazz, id);
	}


	@Override
	public <T extends Entity> T load(Class<T> clazz, Serializable id) { 
		 return entityManager.getReference(clazz, id);
	}


	@Override
	public <T extends Entity> T getUnmodified(Class<T> clazz, T entity) {
		 entityManager.detach(entity);
		 return get(clazz, entity.getId());
	}

	@Override
	public <T extends Entity> T getByBusinessKeys(Class<T> clazz, NamedParameters keyValues) {
		List<T> results = findByProperties(clazz, keyValues);
        return results.isEmpty() ? null : results.get(0);
	}

	@Override
	public <T extends Entity> List<T> findAll(Class<T> clazz) {
		 String queryString = "select o from " + clazz.getName() + " as o";
		 return entityManager.createQuery(queryString).getResultList();
	}

	@Override
	public <T extends Entity> CriteriaQuery createCriteriaQuery(Class<T> entityClass) {
		 return new CriteriaQuery(this, entityClass);
	}

	 @Override
	 public <T extends Entity> List<T> find(Class<T> entityClass, QueryCriterion criterion) {
	        return find(createCriteriaQuery(entityClass).and(criterion));
	    }
	 
	 @Override
	 public <T> List<T> find(CriteriaQuery criteriaQuery) {
	        Query query = entityManager.createQuery(criteriaQuery.getQueryString());
	        processQuery(query, criteriaQuery.getParameters(), 
	                criteriaQuery.getFirstResult(), criteriaQuery.getMaxResults());
	        return query.getResultList();
	    }

	@Override
	public <T> T getSingleResult(CriteriaQuery criteriaQuery) {
		 List<T> results = find(criteriaQuery);
		 return results.isEmpty() ? null : results.get(0);
	}
	
	 @Override
	    public <T extends Entity> T getSingleResult(Class<T> entityClass, QueryCriterion criterion) {
	        return getSingleResult(createCriteriaQuery(entityClass).and(criterion));
	    }

	@Override
	public JpqlQuery createJpqlQuery(String jpql) {
		 return new JpqlQuery(this, jpql);
	}

	@Override
	public <T> List<T> find(JpqlQuery jpqlQuery) {
		 return getQuery(jpqlQuery).getResultList();
	}

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
        Query query = entityManager.createQuery(jpqlQuery.getJpql());
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
	        Query query = entityManager.createNamedQuery(namedQuery.getQueryName());
	        processQuery(query, namedQuery);
	        return query;
	    }

	@Override
	public <T> List<T> find(SqlQuery sqlQuery) {
		return getQuery(sqlQuery).getResultList();
	}

	@Override
	public <T> T getSingleResult(SqlQuery sqlQuery) {
		 try {
	            return (T) getQuery(sqlQuery).getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	}
	
	private Query getQuery(SqlQuery sqlQuery) {
        Query query;
        if (sqlQuery.getResultEntityClass() == null) {
            query = entityManager.createNativeQuery(sqlQuery.getSql());
        } else {
            query = entityManager.createNativeQuery(sqlQuery.getSql(),
                    sqlQuery.getResultEntityClass());
        }
        processQuery(query, sqlQuery);
        return query;
    }

	@Override
	public int executeUpdate(SqlQuery sqlQuery) {
		 return getQuery(sqlQuery).executeUpdate();
	}


	@Override
	public String getQueryStringOfNamedQuery(String queryName) {
		//return getNamedQueryParser().getQueryStringOfNamedQuery(queryName);
		return null;
	}

	@Override
	public void flush() {
		 entityManager.flush();		
	}

	@Override
	public void refresh(Entity entity) {
		 entityManager.refresh(entity);		
	}

	@Override
	public void clear() {
		entityManager.clear();		
	}


}