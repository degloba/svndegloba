package com.degloba.persistence.datanucleus;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.degloba.domain.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.Query;

import com.degloba.domain.CriteriaQuery;
import com.degloba.domain.Entity;
import com.degloba.domain.ExampleSettings;
import com.degloba.domain.InstanceFactory;
import com.degloba.domain.JpqlQuery;
import com.degloba.domain.NamedParameters;
import com.degloba.domain.NamedQuery;
import com.degloba.domain.PositionalParameters;
import com.degloba.domain.QueryParameters;
import com.degloba.domain.SqlQuery;

/**
 * 通用仓储接口的Hibernate实现。
 * <p> EntityRepositoryHibernate通过SessionProvider获取Session，以保证在当前线程和事务中
 * 对数据库的多次访问都是由同一个Session来进行，防止出现“会话已关闭”异常。
 *
 * @author yyang (<a href="mailto:gdyangyu@gmail.com">gdyangyu@gmail.com</a>)
 *
 */
@SuppressWarnings({"unchecked"})
public class EntityRepositoryDatanucleus implements EntityRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityRepositoryDatanucleus.class);

    //private final SessionProvider sessionProvider;
    private EntityManagerProvider entityManagerProvider;
    
    //å‘½å��æŸ¥è¯¢è§£æž�å™¨ï¼Œå®ƒæ˜¯å�¯é€‰çš„
	private NamedQueryParser namedQueryParser;	
	
	
    @PersistenceContext(unitName="transactions-optional")
    //@Qualifier(value="entityManagerFactory")
    protected EntityManager entityManager;
    

/*    public EntityRepositoryDatanucleus() {
        sessionProvider = new SessionProvider();
    }

    public EntityRepositoryDatanucleus(SessionFactory sessionFactory) {
        this.sessionProvider = new SessionProvider(sessionFactory);
    }

    public EntityRepositoryDatanucleus(Session session) {
        this.sessionProvider = new SessionProvider(session);
    }*/
    
    
    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#save(com.degloba.domain.Entity)
     */
    public <T extends com.degloba.domain.seedwork.Entity> T save(T entity) {
        if (entity.notExisted()) {
        	this.entityManager.persist(entity);
            LOGGER.info("create a entity: " + entity.getClass() + "/" + entity.getId() + ".");
            return entity;
        }
        this.entityManager.merge(entity);
        LOGGER.info("update a entity: " + entity.getClass() + "/" + entity.getId() + ".");
        return entity;
    }


    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#remove(com.degloba.domain.Entity)
     */
    public void remove(com.degloba.domain.seedwork.Entity entity) {
    	this.entityManager.remove(entity);
        LOGGER.info("remove a entity: " + entity.getClass() + "/" + entity.getId() + ".");
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#exists(java.lang.Class, java.io.Serializable)
     */
    public <T extends com.degloba.domain.seedwork.Entity> boolean exists(final Class<T> clazz, final Serializable id) {
        return get(clazz, id) != null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.degloba.domain.EntityRepository#get(java.io.Serializable)
     */
    public <T extends com.degloba.domain.seedwork.Entity> T get(final Class<T> clazz, final Serializable id) {
        return (T) this.entityManager.find(clazz, id);
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#load(java.lang.Class, java.io.Serializable)
     */
    public <T extends com.degloba.domain.seedwork.Entity> T load(final Class<T> clazz, final Serializable id) {
        return (T) this.entityManager.getReference(clazz, id);
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#getUnmodified(java.lang.Class, com.degloba.domain.Entity)
     */
    public <T extends com.degloba.domain.seedwork.Entity> T getUnmodified(Class<T> clazz, T entity) {
    	this.entityManager.detach(entity);
        return get(clazz, entity.getId());
    }

    public <T extends com.degloba.domain.seedwork.Entity> T getByBusinessKeys(Class<T> clazz, NamedParameters keyValues) {
        List<T> results = findByProperties(clazz, keyValues);
        return results.isEmpty() ? null : results.get(0);
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#findAll(java.lang.Class)
     */
    public <T extends com.degloba.domain.seedwork.Entity> List<T> findAll(Class<T> clazz) {
    	  String queryString = "select o from " + clazz.getName() + " as o";
	      return this.entityManager.createQuery(queryString).getResultList();
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#createCriteriaQuery(java.lang.Class)
     */
    public <T extends com.degloba.domain.seedwork.Entity> CriteriaQuery createCriteriaQuery(Class<T> entityClass) {
       // return new CriteriaQuery(this, entityClass);
    	return null;
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#find(com.degloba.domain.CriteriaQuery)
     */
    @SuppressWarnings("rawtypes")
    public <T> List<T> find(CriteriaQuery criteriaQuery) {
        Query query = this.entityManager.createQuery(criteriaQuery.getQueryString());
        processQuery(query, criteriaQuery.getParameters(), 
                criteriaQuery.getFirstResult(), criteriaQuery.getMaxResults());
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#getSingleResult(com.degloba.domain.CriteriaQuery)
     */
    public <T> T getSingleResult(CriteriaQuery dddQuery) {
        List<T> results = find(dddQuery);
        return results == null || results.isEmpty() ? null : results.get(0);
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#createJpqlQuery(java.lang.String)
     */
    public JpqlQuery createJpqlQuery(String jpql) {
        return new JpqlQuery(this, jpql);
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#find(com.degloba.domain.JpqlQuery)
     */
    public <T> List<T> find(JpqlQuery jpqlQuery) {
        return getQuery(jpqlQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#getSingleResult(com.degloba.domain.JpqlQuery)
     */
    public <T> T getSingleResult(JpqlQuery jpqlQuery) {
        return (T) getQuery(jpqlQuery).getSingleResult();
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#executeUpdate(com.degloba.domain.JpqlQuery)
     */
    public int executeUpdate(JpqlQuery jpqlQuery) {
        return getQuery(jpqlQuery).executeUpdate();
    }

    private Query getQuery(JpqlQuery jpqlQuery) {
        Query query = this.entityManager.createQuery(jpqlQuery.getJpql());
        processQuery(query, jpqlQuery);
        return query;
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#createNamedQuery(java.lang.String)
     */
    public NamedQuery createNamedQuery(String queryName) {
        return new NamedQuery(this, queryName);
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#find(com.degloba.domain.NamedQuery)
     */
    public <T> List<T> find(NamedQuery namedQuery) {
        return getQuery(namedQuery).getResultList();
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#getSingleResult(com.degloba.domain.NamedQuery)
     */
    public <T> T getSingleResult(NamedQuery namedQuery) {
        return (T) getQuery(namedQuery).getSingleResult();
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#executeUpdate(com.degloba.domain.NamedQuery)
     */
    public int executeUpdate(NamedQuery namedQuery) {
        return getQuery(namedQuery).executeUpdate();
    }

    private Query getQuery(NamedQuery namedQuery) {
        Query query = this.entityManager.createNamedQuery(namedQuery.getQueryName());
        processQuery(query, namedQuery);
        return query;
    }

    public SqlQuery createSqlQuery(String sql) {
        return new SqlQuery(this, sql);
    }

    @SuppressWarnings("rawtypes")
    public <T> List<T> find(SqlQuery sqlQuery) {
        return getQuery(sqlQuery).getResultList();
    }

    public <T> T getSingleResult(SqlQuery sqlQuery) {
        return (T) getQuery(sqlQuery).getSingleResult();
    }

    public int executeUpdate(SqlQuery sqlQuery) {
        return getQuery(sqlQuery).executeUpdate();
    }

    private Query getQuery(SqlQuery sqlQuery) {
    	 Query query;
	        if (sqlQuery.getResultEntityClass() == null) {
	            query = this.entityManager.createNativeQuery(sqlQuery.getSql());
	        } else {
	            query = this.entityManager.createNativeQuery(sqlQuery.getSql(),
	                    sqlQuery.getResultEntityClass());
	        }
	        processQuery(query, sqlQuery);
	        return query;
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#findByExample(com.degloba.domain.Entity, com.degloba.domain.ExampleSettings)
     */
    public <T extends com.degloba.domain.seedwork.Entity, E extends T> List<T> findByExample(final E example, final ExampleSettings<T> settings) {
     /*   Example theExample = Example.create(example);
        if (settings.isLikeEnabled()) {
            theExample.enableLike(MatchMode.ANYWHERE);
        }
        if (settings.isIgnoreCaseEnabled()) {
            theExample.ignoreCase();
        }
        if (settings.isExcludeNone()) {
            theExample.excludeNone();
        }
        if (settings.isExcludeZeroes()) {
            theExample.excludeZeroes();
        }
        for (String propName : settings.getExcludedProperties()) {
            theExample.excludeProperty(propName);
        }
       return this.entityManager.createCriteria(settings.getEntityClass()).add(theExample).list();  */ 
		throw new RuntimeException("not implemented yet!");
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#findByProperty(java.lang.Class, java.lang.String, java.lang.Object)
     */
    public <T extends com.degloba.domain.seedwork.Entity> List<T> findByProperty(Class<T> clazz, String propertyName, Object propertyValue) {
        //return find(new CriteriaQuery(this, clazz).eq(propertyName, propertyValue));
        return null;
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#findByProperties(java.lang.Class, java.util.Map)
     */
    public <T extends com.degloba.domain.seedwork.Entity> List<T> findByProperties(Class<T> clazz, NamedParameters properties) {
/*        CriteriaQuery criteriaQuery = new CriteriaQuery(this, clazz);
        for (Map.Entry<String, Object> each : properties.getParams().entrySet()) {
            criteriaQuery = criteriaQuery.eq(each.getKey(), each.getValue());
        }
        return find(criteriaQuery);*/
    	return null;
    }

    public String getQueryStringOfNamedQuery(String queryName) {
        return getNamedQueryParser().getQueryStringOfNamedQuery(queryName);
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#flush()
     */
    public void flush() {
    	this.entityManager.flush();
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#refresh(com.degloba.domain.Entity)
     */
    public void refresh(com.degloba.domain.seedwork.Entity entity) {
    	this.entityManager.refresh(entity);
    }

    /*
     * (non-Javadoc)
     * @see com.degloba.domain.EntityRepository#clear()
     */
    public void clear() {
    	this.entityManager.clear();
    }

/*    Session getSession() {
        return sessionProvider.getSession();
    }*/

    private void processQuery(Query query, BaseQuery originQuery) {
        processQuery(query, originQuery.getParameters(), originQuery.getFirstResult(), 
                originQuery.getMaxResults());
        fillParameters(query, originQuery.getParameters());
        query.setFirstResult(originQuery.getFirstResult());
        if (originQuery.getMaxResults() > 0) {
            query.setMaxResults(originQuery.getMaxResults());
        }
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
            throw new UnsupportedOperationException("不支持的参数形式");
        }
    }

    private void fillParameters(Query query, PositionalParameters params) {
        Object[] paramArray = params.getParams();
        for (int i = 0; i < paramArray.length; i++) {
            query = query.setParameter(i, paramArray[i]);
        }
    }

    private void fillParameters(Query query, NamedParameters params) {
        for (Map.Entry<String, Object> each : params.getParams().entrySet()) {
            query = query.setParameter(each.getKey(), each.getValue());
        }
    }
    
    private NamedQueryParser getNamedQueryParser() {
        if (namedQueryParser == null) {
            namedQueryParser = InstanceFactory.getInstance(NamedQueryParser.class);
        }
        namedQueryParser.setEntityManagerProvider(entityManagerProvider);
        return namedQueryParser;
    }


	@Override
	public <T extends com.degloba.domain.seedwork.Entity> boolean exists(
			Class<T> clazz, Long id) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public <T extends com.degloba.domain.seedwork.Entity> T get(Class<T> clazz,
			Long id) {
		// TODO Auto-generated method stub
		return null;
	}



}