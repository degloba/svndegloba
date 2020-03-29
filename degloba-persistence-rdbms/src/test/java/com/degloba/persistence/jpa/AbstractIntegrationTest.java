package com.degloba.persistence.jpa;

//import com.degloba.btm.BtmUtils;

// Classes de domini
import com.degloba.domain.AbstractEntity;
import com.degloba.domain.BaseAggregateRoot;

import com.degloba.domain.InstanceFactory;
import com.degloba.ioc.spring.factory.SpringInstanceProvider;

// Per testejar appengine
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.SystemException;

/*
 * RunWith = Indicates that the class should use Spring's JUnit facilities
 * ContextConfiguration = defines class-level metadata that is used to determine how to load and configure an
 *                        ApplicationContext for integration tests
 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(transactionManager = "txManager")
//@Transactional
@EnableTransactionManagement
public class AbstractIntegrationTest {
	
	private static EntityManagerFactory emf;

    protected EntityManager entityManager;
    
    protected JpaEntityRepository repository;
    
	//	Injectem l'ApplicationContext d'Spring gràcies a ContextConfiguration
    @Autowired
    private ApplicationContext applicationContext;
    
	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig()
	        .setApplyAllHighRepJobPolicy());
	
/*	No funciona private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig()
            .setDefaultHighRepJobPolicyUnappliedJobPercentage(100));*/

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        SpringInstanceProvider provider = new SpringInstanceProvider(applicationContext);
        InstanceFactory.setInstanceProvider(provider);
        
        InstanceFactory.bind(EntityManagerFactory.class, emf);
        repository = new JpaEntityRepository(emf);
        AbstractEntity.setRepository(repository);
        entityManager = repository.getEntityManager();
        
    	helper.setUp();  
    }

    @After
    public void tearDown() throws IllegalStateException, SystemException {
    	InstanceFactory.setInstanceProvider(null);
    	
    	entityManager.close();
        repository = null;
        AbstractEntity.setRepository(null);
    	
        helper.tearDown();
    }
}
