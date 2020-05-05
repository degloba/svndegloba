package com.degloba.persistence.rdbms.api.jpa;


import com.degloba.ioc.InstanceFactory;
import com.degloba.ioc.sharedkernel.exceptions.IocInstanceNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


/**
 * JPA EntityManagerProvider. Si el thread actual de entityManager encara no existeix,
 * obté un thread des del contenidor i botiga IoC,
 * Si el thread actual ja existeix les variables de subjunt entityManager, el retorna.
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
