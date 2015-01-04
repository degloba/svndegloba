package infrastructure.repository.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

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


import domain.seedwork.IRepository;
import domain.support.BaseAggregateRoot;
import domain.support.BaseQuery;
import domain.support.CriteriaQuery;
import domain.support.ExampleSettings;
import domain.support.InstanceFactory;
import domain.support.JpqlQuery;
import domain.support.NamedParameters;
import domain.support.NamedQuery;
import domain.support.PositionalParameters;
import domain.support.QueryParameters;
import domain.support.SqlQuery;


/**
 * 
 * @author degloba
 * 
 * @param <E> JPA Entity Type (DDD: Aggregate, Entity)
 * @param <K>  Tipus de la clau (Long,String,aggregateId, ..) de l'entitat del domini
 *            
 */
public class BaseAggregateRootJpaRepository implements IBaseAggregateRootJpaRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseAggregateRootJpaRepository.class);

    //å‘½å��æŸ¥è¯¢è§£æž�å™¨ï¼Œå®ƒæ˜¯å�¯é€‰çš„
	private NamedQueryParser namedQueryParser;	    
    
	private EntityManagerProvider entityManagerProvider;


    @PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactory")
    protected EntityManager entityManager;

     private Class<BaseAggregateRoot> clazz;

    @SuppressWarnings("unchecked")
    public BaseAggregateRootJpaRepository() {
        this.clazz = ((Class<BaseAggregateRoot>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
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
     * @see infrastructure.repository.jpa.IBaseAggregateRootJpaRepository#load(java.lang.Object)
     * Retorn una entitat BaseAggregateRoot a partir del seu id
     */
/*    @Override
	public <K load(K id) {
    	return this.entityManager.find(clazz, id);
	}*/

    
	/* (non-Javadoc)
	 * @see infrastructure.repository.jpa.IBaseAggregateRootJpaRepository#delete(java.lang.Object)
	 * Esborra una entitat BaseAggregateRoot a partir del seu id
	 */
/*	@Override
	public void delete(K id) {
        this.entityManager.remove(load(id));
	}
*/
	@Override
	public <E extends BaseAggregateRoot> void remove(E entity) {
		this.entityManager.remove(entity);
	}
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dayatang.domain.EntityRepository#remove(org.dayatang.domain.Entity)
     */
/*    @Override
    public <E> void remove(E entity) {
        getEntityManager().remove(get(entity.getClass(), entity.getId()));
        LOGGER.info("remove a entity: " + entity.getClass() + "/"
                + entity.getId() + ".");
    }*/
	
	/* (non-Javadoc)
	 * @see infrastructure.repository.jpa.IBaseAggregateRootJpaRepository#persist(java.lang.Object)
	 * Crea una entitat BaseAggregateRoot
	 */
	@Override
	public <E extends BaseAggregateRoot> void persist(E entitat) {
		this.entityManager.persist(entitat);		
	}

	/* (non-Javadoc)
	 * @see infrastructure.repository.jpa.IBaseAggregateRootJpaRepository#save(java.lang.Object)
	 * Modifica una entitat BaseAggregateRoot
	 */
/*	@Override
	public E save(E entitat) {
		return this.entityManager.merge(entitat);
	}*/
    @Override
    public BaseAggregateRoot save(BaseAggregateRoot entity) {
        if (entity.notExisted()) {
            getEntityManager().persist(entity);
            LOGGER.info("create a entity: " + entity.getClass() + "/"
                    + entity.getId() + ".");
            return entity;
        }
        BaseAggregateRoot result = getEntityManager().merge(entity);
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
/*	    @Override
	    public <T extends Entity> boolean exists(final Class<T> clazz,
	                                             final Serializable id) {
	        T entity = getEntityManager().find(clazz, id);
	        return entity != null;
	    }*/


	    /*
	     * (non-Javadoc)
	     * 
	     * @see org.dayatang.domain.EntityRepository#get(java.io.Serializable)
	     */
/*	    @Override
	    public <E extends Entity> E get(final Class<E> clazz, final Serializable id) {
	        return getEntityManager().find(clazz, id);
	    }
*/
	    /*
	     * (non-Javadoc)
	     * 
	     * @see org.dayatang.domain.EntityRepository#load(java.io.Serializable)
	     */
	


/*	    @Override
	    public <E extends BaseAggregateRoot> List<E> findAll(final Class<E> clazz) {
	        String queryString = "select o from " + clazz.getName() + " as o";
	        return getEntityManager().createQuery(queryString).getResultList();
	    }*/

	    @Override
	    public <E extends BaseAggregateRoot> List<E> find(CriteriaQuery criteriaQuery) {
	        Query query = getEntityManager().createQuery(criteriaQuery.getQueryString());
	        processQuery(query, criteriaQuery.getParameters(), 
	                criteriaQuery.getFirstResult(), criteriaQuery.getMaxResults());
	        return query.getResultList();
	    }

	   

	    @Override
	    public JpqlQuery createJpqlQuery(String jpql) {
	        return new JpqlQuery((IRepository) this, jpql);
	    }

	    @Override
	    public <E extends BaseAggregateRoot> E get(Class<E> clazz, Serializable id)
	    {
	    	return null;
	    }
	    
	    @Override
	    public <E extends BaseAggregateRoot> E load(Class<E> clazz, Serializable id)
	    {
	    	return null;
	    }

	    @Override
	    public <E extends BaseAggregateRoot> E getSingleResult(JpqlQuery jpqlQuery) {
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
	        return new NamedQuery((IRepository) this, queryName);
	    }

	    @Override
	    public <E extends BaseAggregateRoot> List<E> find(NamedQuery namedQuery) {
	        return getQuery(namedQuery).getResultList();
	    }

	    @Override
	    public <E extends BaseAggregateRoot> E getSingleResult(NamedQuery namedQuery) {
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

	    private Query getQuery(NamedQuery namedQuery) {
	        Query query = getEntityManager().createNamedQuery(namedQuery.getQueryName());
	        processQuery(query, namedQuery);
	        return query;
	    }

	    @Override
	    public SqlQuery createSqlQuery(String sql) {
	        return new SqlQuery((IRepository) this, sql);
	    }

	    @Override
	    public <E extends BaseAggregateRoot> List<E> find(SqlQuery sqlQuery) {
	        return getQuery(sqlQuery).getResultList();
	    }

	    @Override
	    public <E extends BaseAggregateRoot> E getSingleResult(SqlQuery sqlQuery) {
	        try {
	            return (E) getQuery(sqlQuery).getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	    }
	    
	    @Override
	    public <E extends BaseAggregateRoot> E getSingleResult(CriteriaQuery sqlQuery) {
	        
	            return null;
	        
	    }
	    
	    @Override
	    public <E extends BaseAggregateRoot> List<E> findAll(Class<E> clazz)
	    {
	    	return null;
	    }
	    
	    @Override
	    public <E extends BaseAggregateRoot> boolean exists(Class<E> clazz, Serializable id)
	    {
	    	return false;
	    }
	    
	    @Override
	    public <E extends BaseAggregateRoot> CriteriaQuery createCriteriaQuery(Class<E> clazz)
	    {
	    	return null;
	    }
	    
	    @Override
	    public <E extends BaseAggregateRoot> List<E> find(JpqlQuery jpqlQuery)
	    {
	    	return null;
	    }
	    
	    public <E extends BaseAggregateRoot> List<E> findByProperties(Class<E> clazz, NamedParameters properties)
	    {
	    	return null;
	    }
	    
	    @Override
	    public <E extends BaseAggregateRoot> List<E> findByProperty(Class<E> clazz, String propertyName, Object propertyValue)
	    {
	    	return null;
	    }
	    
	    @Override
	    public <E extends BaseAggregateRoot> E getByBusinessKeys(Class<E> clazz, NamedParameters keyValues)
	    {
	    	return null;
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




	    
	    private Query getQuery(JpqlQuery jpqlQuery) {
	        Query query = getEntityManager().createQuery(jpqlQuery.getJpql());
	        processQuery(query, jpqlQuery);
	        return query;
	    }

		

			

			

		@Override
		public <K, E> E load(K id) {
			// TODO Auto-generated method stub
			return null;
		}

				
		@Override
		public <E extends BaseAggregateRoot> E getUnmodified(Class<E> clazz, E entity) {
			// TODO Auto-generated method stub
			return null;
		}

		

		@Override
		public <E, E2 extends E> List<E> findByExample(E2 example,
				ExampleSettings<E> settings) {
			// TODO Auto-generated method stub
			return null;
		}

		
		

		@Override
		public <E> void refresh(E entity) {
			// TODO Auto-generated method stub
			
		}


	    
	    /*************************************************/
	    
	    
	    
}
