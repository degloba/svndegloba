package com.degloba.persistence.jpa;

//import org.dayatang.btm.BtmUtils;

// Classes de domini
import com.degloba.domain.AbstractEntity;
import com.degloba.domain.BaseAggregateRoot;
import com.degloba.domain.EntityRepository;
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
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;

import javax.transaction.SystemException;

/*
 * RunWith = Indicates that the class should use Spring's JUnit facilities
 * ContextConfiguration = defines class-level metadata that is used to determine how to load and configure an
 *                        ApplicationContext for integration tests
 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager")
@EnableTransactionManagement
public class AbstractIntegrationTest {
    
	//	Injectem l'ApplicationContext d'Spring gràcies a ContextConfiguration
    @Inject
    private ApplicationContext applicationContext;
    
	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig()
	        .setApplyAllHighRepJobPolicy());

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
        
    	helper.setUp();  
    }

    @After
    public void tearDown() throws IllegalStateException, SystemException {
    	InstanceFactory.setInstanceProvider(null);
    	
        helper.tearDown();
    }
}
