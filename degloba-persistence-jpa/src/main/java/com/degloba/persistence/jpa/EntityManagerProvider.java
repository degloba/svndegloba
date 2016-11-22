package com.degloba.persistence.jpa;

import com.degloba.domain.ioc.InstanceFactory;
import com.degloba.domain.sharedkernel.exceptions.IocInstanceNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


/**
 * JPA EntityManagerProvider. If entityManager thread variable current thread does not yet exist, 
 * from the IoC container and store Get a current thread,
 * If the current thread already entityManager thread variables exist, direct return.
 *
 * This class exists primarily to the current thread, return the same entityManager objects on each request. 
 * Avoid affairs and "session was closed" problem.
 *
 */
public class EntityManagerProvider {

    private EntityManagerFactory entityManagerFactory;

    private final ThreadLocal<EntityManager> entityManagerHolder = new ThreadLocal<EntityManager>();

    public EntityManagerProvider() {
        entityManagerFactory = InstanceFactory.getInstance(EntityManagerFactory.class);
    }

    public EntityManagerProvider(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public EntityManagerProvider(EntityManager entityManager) {
        entityManagerHolder.set(entityManager);
    }

    public EntityManager getEntityManager() {
        EntityManager result = entityManagerHolder.get();
        if (result != null && result.isOpen()) {
            return result;
        }
        result = getEntityManagerFromIoC();
        entityManagerHolder.set(result);
        return result;
    }

    public EntityManager getEntityManagerFromIoC() {
        try {
            return InstanceFactory.getInstance(EntityManager.class);
        } catch (IocInstanceNotFoundException e) {
            if (entityManagerFactory == null) {
                entityManagerFactory = InstanceFactory.getInstance(EntityManagerFactory.class);
            }
            return entityManagerFactory.createEntityManager();
        }
    }
}
