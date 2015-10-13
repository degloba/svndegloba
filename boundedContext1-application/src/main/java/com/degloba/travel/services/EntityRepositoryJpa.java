package com.degloba.travel.services;


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
public class EntityRepositoryJpa implements EntityRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityRepositoryJpa.class);

   

	@PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactory")
    protected EntityManager entityManager;
    
	
    
    public EntityRepositoryJpa() {
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
       /* query.setFirstResult(firstResult);
        if (maxResults > 0) {
            query.setMaxResults(maxResults);
        }*/
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













	@Override
	public <T extends Entity> T save(T entity) {
		// TODO Auto-generated method stub
		return null;
	}













	@Override
	public void remove(Entity entity) {
		// TODO Auto-generated method stub
		
	}













	@Override
	public <T extends Entity> boolean exists(Class<T> clazz, Key id) {
		// TODO Auto-generated method stub
		return false;
	}













	@Override
	public <T extends Entity> T get(Class<T> clazz, Key id) {
		// TODO Auto-generated method stub
		return null;
	}













	@Override
	public <T extends Entity> T load(Class<T> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}













	@Override
	public <T extends Entity> T getUnmodified(Class<T> clazz, T entity) {
		// TODO Auto-generated method stub
		return null;
	}













	@Override
	public <T extends Entity> T getByBusinessKeys(Class<T> clazz, NamedParameters keyValues) {
		// TODO Auto-generated method stub
		return null;
	}













	@Override
	public <T extends Entity> List<T> findAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}













	@Override
	public <T extends Entity> CriteriaQuery createCriteriaQuery(Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}













	@Override
	public <T> List<T> find(CriteriaQuery criteriaQuery) {
		// TODO Auto-generated method stub
		return null;
	}













	@Override
	public <T> T getSingleResult(CriteriaQuery criteriaQuery) {
		// TODO Auto-generated method stub
		return null;
	}













	@Override
	public JpqlQuery createJpqlQuery(String jpql) {
		// TODO Auto-generated method stub
		return null;
	}













	@Override
	public <T> List<T> find(JpqlQuery jpqlQuery) {
		// TODO Auto-generated method stub
		return null;
	}













	@Override
	public <T> T getSingleResult(JpqlQuery jpqlQuery) {
		// TODO Auto-generated method stub
		return null;
	}













	@Override
	public int executeUpdate(JpqlQuery jpqlQuery) {
		// TODO Auto-generated method stub
		return 0;
	}













	@Override
	public NamedQuery createNamedQuery(String queryName) {
		// TODO Auto-generated method stub
		return null;
	}













	@Override
	public <T> List<T> find(NamedQuery namedQuery) {
		// TODO Auto-generated method stub
		return null;
	}













	@Override
	public <T> T getSingleResult(NamedQuery namedQuery) {
		// TODO Auto-generated method stub
		return null;
	}













	@Override
	public int executeUpdate(NamedQuery namedQuery) {
		// TODO Auto-generated method stub
		return 0;
	}













	@Override
	public <T> List<T> find(SqlQuery sqlQuery) {
		// TODO Auto-generated method stub
		return null;
	}













	@Override
	public <T> T getSingleResult(SqlQuery sqlQuery) {
		// TODO Auto-generated method stub
		return null;
	}













	@Override
	public int executeUpdate(SqlQuery sqlQuery) {
		// TODO Auto-generated method stub
		return 0;
	}













	@Override
	public String getQueryStringOfNamedQuery(String queryName) {
		// TODO Auto-generated method stub
		return null;
	}













	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}













	@Override
	public void refresh(Entity entity) {
		// TODO Auto-generated method stub
		
	}













	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}


}
