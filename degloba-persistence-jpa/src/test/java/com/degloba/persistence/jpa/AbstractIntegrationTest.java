package com.degloba.persistence.jpa;

import org.dayatang.btm.BtmUtils;

import com.degloba.domain.AbstractEntity;
import com.degloba.domain.InstanceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.SystemException;

public class AbstractIntegrationTest {

    private static BtmUtils btmUtils;

    private static EntityManagerFactory emf;

    protected EntityManager entityManager;

    private EntityTransaction tx;

    protected EntityRepositoryJpa repository;
    
	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig()
	        .setApplyAllHighRepJobPolicy());

    @BeforeClass
    public static void setUpClass() throws Exception {
        //btmUtils = BtmUtils.readConfigurationFromClasspath("/datasources.properties");
        //btmUtils.setupDataSource();
        emf = Persistence.createEntityManagerFactory("transactions-optional");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        emf.close();
        //btmUtils.closeDataSource();
        //btmUtils = null;
        System.out.println("================================================");
        System.out.println("Close BTM");
    }

    @Before
    public void setUp() {
    	helper.setUp();  
    	
        InstanceFactory.bind(EntityManagerFactory.class, emf);
        repository = new EntityRepositoryJpa(emf);
        AbstractEntity.setRepository(repository);
        entityManager = repository.getEntityManager();
        tx = entityManager.getTransaction();
        tx.begin();
    }

    @After
    public void tearDown() throws IllegalStateException, SystemException {
        tx.rollback();
        entityManager.close();
        repository = null;
        AbstractEntity.setRepository(null);
        
        helper.tearDown();
    }
}
