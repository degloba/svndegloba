package com.degloba.persistence.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

// JPA
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Spring
import org.springframework.beans.factory.annotation.Qualifier;

import com.degloba.domain.BaseAggregateRoot;
import com.degloba.domain.BaseQuery;
import com.degloba.domain.CriteriaQuery;
import com.degloba.domain.EntityRepository;
import com.degloba.domain.ExampleSettings;
import com.degloba.domain.IBaseAggregateRootJpaRepository;
import com.degloba.domain.InstanceFactory;
import com.degloba.domain.JpqlQuery;
import com.degloba.domain.NamedParameters;
import com.degloba.domain.NamedQuery;
import com.degloba.domain.PositionalParameters;
import com.degloba.domain.QueryParameters;
import com.degloba.domain.SqlQuery;


/**
 * 
 * @author degloba
 * 
 * @param <E> JPA Entity Type (DDD: Aggregate, Entity)
 * @param <K>  Tipus de la clau (Long,String,aggregateId, ..) de l'entitat del domini
 *            
 */
public class BaseAggregateRootJpaRepository<E extends BaseAggregateRoot> implements IBaseAggregateRootJpaRepository<E> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseAggregateRootJpaRepository.class);

    //å‘½å��æŸ¥è¯¢è§£æž�å™¨ï¼Œå®ƒæ˜¯å�¯é€‰çš„
	private NamedQueryParser namedQueryParser;	    
    
	private EntityManagerProvider entityManagerProvider;


    @PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactory")
    protected EntityManager entityManager;

    //private Class<E> clazz;

    public BaseAggregateRootJpaRepository() {
        //this.clazz = ((Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }
    
/*    public BaseAggregateRootJpaRepository() {
        entityManagerProvider = new EntityManagerProvider();
    }*/
    
    public BaseAggregateRootJpaRepository(EntityManager entityManager) {
        entityManagerProvider = new EntityManagerProvider(entityManager);
    }

    public BaseAggregateRootJpaRepository(EntityManagerFactory entityManagerFactory) {
        entityManagerProvider = new EntityManagerProvider(entityManagerFactory);
    }

    public BaseAggregateRootJpaRepository(NamedQueryParser namedQueryParser, EntityManagerFactory entityManagerFactory) {
        this(entityManagerFactory);
        setNamedQueryParser(namedQueryParser);
    }

   
	/* (non-Javadoc)
	 * @see infrastructure.repository.jpa.IBaseAggregateRootJpaRepository#delete(java.lang.Object)
	 * Esborra una entitat BaseAggregateRoot a partir del seu id
	 */
	@Override
	public  void remove(E entity) {
		this.entityManager.remove(entity);
	}

	
	/* (non-Javadoc)
	 * @see infrastructure.repository.jpa.IBaseAggregateRootJpaRepository#persist(java.lang.Object)
	 * Crea una entitat BaseAggregateRoot
	 */
	@Override
	public void persist(E entitat) {
		this.entityManager.persist(entitat);		
	}

	/* (non-Javadoc)
	 * @see infrastructure.repository.jpa.IBaseAggregateRootJpaRepository#save(java.lang.Object)
	 * Modifica una entitat BaseAggregateRoot
	 */
    @Override
    public E save(E entity) {
        if (entity.notExisted()) {
            getEntityManager().persist(entity);
            LOGGER.info("create a entity: " + entity.getClass() + "/"
                    + entity.getId() + ".");
            return entity;
        }
        E result = getEntityManager().merge(entity);
        LOGGER.info("update a entity: " + entity.getClass() + "/"
                + entity.getId() + ".");
        return result;
    }
	

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
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

	    /*public EntityManager getEntityManager() {
	        return entityManagerProvider.getEntityManager();
	    }*/
		public EntityManager getEntityManager() {
			return this.entityManager;
		}

	    /*
	     * (non-Javadoc)
	     * 
	     * @see org.dayatang.domain.EntityRepository#exists(java.io.Serializable)
	     */
	    @Override
	    public  boolean exists(Class<E> clazz, Serializable id)
	    {
	        E entity = getEntityManager().find(clazz, id);
	        return entity != null;
	    }

	    /*
	     * (non-Javadoc)
	     * 
	     * @see org.dayatang.domain.EntityRepository#get(java.io.Serializable)
	     */
	    @Override
	    public  E get(Class<E> clazz, Serializable id)
	    {
	    	return getEntityManager().find(clazz, id);
	    }

	    /*
	     * (non-Javadoc)
	     * 
	     * @see org.dayatang.domain.EntityRepository#load(java.io.Serializable)
	     */
	    @Override
	    public  E load(Class<E> clazz, Serializable id)
	    {
	    	return getEntityManager().getReference(clazz, id);
	    }

	    @SuppressWarnings("unchecked")
		@Override
	    public  List<E> find(CriteriaQuery criteriaQuery) {
	        Query query = getEntityManager().createQuery(criteriaQuery.getQueryString());
	        processQuery(query, criteriaQuery.getParameters(), 
	                criteriaQuery.getFirstResult(), criteriaQuery.getMaxResults());
	        return query.getResultList();
	    }	   

	    @Override
	    public JpqlQuery createJpqlQuery(String jpql) {
	        return new JpqlQuery((EntityRepository) this, jpql);
	    }

	    @SuppressWarnings("unchecked")
		@Override
	    public E getSingleResult(JpqlQuery jpqlQuery) {
	        try {
	            return (E) getQuery(jpqlQuery).getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	    }

	    @Override
	    public int executeUpdate(JpqlQuery jpqlQuery) {
	        return getQuery(jpqlQuery).executeUpdate();
	    }

	    @Override
	    public NamedQuery createNamedQuery(String queryName) {
	        return new NamedQuery((EntityRepository) this, queryName);
	    }

	    @SuppressWarnings("unchecked")
		@Override
	    public List<E> find(NamedQuery namedQuery) {
	        return getQuery(namedQuery).getResultList();
	    }

	    @SuppressWarnings("unchecked")
		@Override
	    public  E getSingleResult(NamedQuery namedQuery) {
	        try {
	            return (E) getQuery(namedQuery).getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	    }

	    @Override
	    public int executeUpdate(NamedQuery namedQuery) {
	        return getQuery(namedQuery).executeUpdate();
	    }
	    

	    @Override
	    public SqlQuery createSqlQuery(String sql) {
	        return new SqlQuery((EntityRepository) this, sql);
	    }

	    
		@SuppressWarnings("unchecked")
		@Override
	    public List<E> find(SqlQuery sqlQuery) {
	        return getQuery(sqlQuery).getResultList();
	    }

	    @SuppressWarnings("unchecked")
		@Override
	    public  E getSingleResult(SqlQuery sqlQuery) {
	        try {
	            return (E) getQuery(sqlQuery).getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	    }
	    
	    @Override
	    public  E getSingleResult(CriteriaQuery dddQuery) {	        
	        List<E> results = find(dddQuery);
	        return results.isEmpty() ? null : results.get(0);	        
	    }
	    
	    @SuppressWarnings("unchecked")
		@Override
	    public  List<E> findAll(Class<E> clazz)
	    {
	        String queryString = "select o from " + clazz.getName() + " as o";
	        return getEntityManager().createQuery(queryString).getResultList();
	    }	    
	    
	    @Override
	    public CriteriaQuery createCriteriaQuery(Class<E> entityClass)
	    {
	    	return new CriteriaQuery((EntityRepository)this, entityClass);
	    }
	    
	    @SuppressWarnings("unchecked")
		@Override
	    public  List<E> find(JpqlQuery jpqlQuery)
	    {
	    	return getQuery(jpqlQuery).getResultList();
	    }
	    
	    @SuppressWarnings("unchecked")
		@Override
	    public List<E> findByProperties(Class<E> clazz, NamedParameters properties)
	    {
	        CriteriaQuery criteriaQuery = new CriteriaQuery((EntityRepository)this, clazz);
	        for (Map.Entry<String, Object> each : properties.getParams().entrySet()) {
	            criteriaQuery = (CriteriaQuery) criteriaQuery.eq(each.getKey(), each.getValue());
	        }
	        return find(criteriaQuery);
	    }
	    
		@Override
	    public List<E> findByProperty(Class<E> clazz, String propertyName, Object propertyValue)
	    {
	        return (List<E>) find(new CriteriaQuery((EntityRepository)this, clazz).eq(propertyName, propertyValue));
	    }
	    
	    @Override
	    public E getByBusinessKeys(Class<E> clazz, NamedParameters keyValues)
	    {
	        List<E> results = findByProperties(clazz, keyValues);
	        return results.isEmpty() ? null : results.get(0);
	    }
	        

	    @Override
	    public int executeUpdate(SqlQuery sqlQuery) {
	        return getQuery(sqlQuery).executeUpdate();
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
	    public void clear() {
	        getEntityManager().clear();
	    }

	   	    	    
		@Override
		public <K, E> E load(K id) {
			// TODO Auto-generated method stub
			return null;
		}

				
		@Override
		public  E getUnmodified(Class<E> clazz, E entity) {
	        getEntityManager().detach(entity);
	        return get(clazz, entity.getId());
		}
		

		@Override
		public <E, E2 extends E> List<E> findByExample(E2 example,
				ExampleSettings<E> settings) {
			throw new RuntimeException("not implemented yet!");
		}
		

		@Override
		public void refresh(E entity) {
	        getEntityManager().refresh(entity);			
		}
	    
		
		private Query getQuery(JpqlQuery jpqlQuery) {
	        Query query = getEntityManager().createQuery(jpqlQuery.getJpql());
	        processQuery(query, jpqlQuery);
	        return query;
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
	            throw new UnsupportedOperationException("ä¸�æ”¯æŒ�çš„å�‚æ•°å½¢å¼�");
	        }
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

		private Query getQuery(NamedQuery namedQuery) {
	        Query query = getEntityManager().createNamedQuery(namedQuery.getQueryName());
	        processQuery(query, namedQuery);
	        return query;
	    }


 
}
