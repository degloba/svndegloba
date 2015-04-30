package com.degloba.persistence.jpa;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;


/**
 * 
 * @author degloba
 * 
 * @param <E> JPA Entity Type (DDD: Aggregate, Entity)
 * @param <K> Key type (Long,String,aggregateId, ..) of JPA Entity
 *            
 */
public class BaseAggregateRootJpaMongoDBRepository<K, E>{

    @PersistenceContext(unitName="mongodb")
    @Qualifier(value="entityManagerFactoryMongo")
    protected EntityManager entityManager;

    private Class<E> clazz;

    @SuppressWarnings("unchecked")
    public BaseAggregateRootJpaMongoDBRepository() {
        this.clazz = ((Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public E load(K id) {
        return this.entityManager.find(clazz, id);
    }

    public void delete(K id) {
        this.entityManager.remove(load(id));
    }

    public void persist(E entity) {
        this.entityManager.persist(entity);
    }

    public E save(E entity) {
        return this.entityManager.merge(entity);
    }

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
