package com.degloba.persistence.mongo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


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
public class EntityRepositoryMongo implements com.degloba.domain.EntityRepositoryMongo {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityRepositoryMongo.class);

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
	public <T extends Entity> List<T> findAll(Class<T> clazz) {
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
	public <T extends Entity, E extends T> List<T> findByExample(E example,
			ExampleSettings<T> settings) {
		// TODO Auto-generated method stub
		return null;
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
