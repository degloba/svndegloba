package com.degloba.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.degloba.ioc.InstanceFactory;
import com.degloba.persistence.rdbms.api.jpa.EntityManagerProvider;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;


public class EntityManagerProviderTest {

    private EntityManagerFactory entityManagerFactory;
    
    private EntityManager entityManager;
    
    @BeforeEach
    public void setUp() {
        entityManagerFactory = mock(EntityManagerFactory.class);
        entityManager = mock(EntityManager.class);
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
    }

    @Test
    public void testDefaultConstructor() {
        InstanceFactory.bind(EntityManagerFactory.class, entityManagerFactory);
        EntityManagerProvider instance = new EntityManagerProvider();
        EntityManager entityManager2 = instance.getEntityManager();
        assertSame(entityManager2, entityManager);
        EntityManager entityManager3 = instance.getEntityManager();
        assertSame(entityManager3, entityManager);
    }

    @Test
    public void testConstructorEntityManagerFactory() {
        EntityManagerProvider instance = new EntityManagerProvider(entityManagerFactory);
        EntityManager entityManager2 = instance.getEntityManager();
        assertSame(entityManager2, entityManager);
        EntityManager entityManager3 = instance.getEntityManager();
        assertSame(entityManager3, entityManager);
    }
}
