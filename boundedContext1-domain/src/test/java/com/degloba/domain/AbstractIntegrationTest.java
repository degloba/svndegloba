package com.degloba.domain;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.degloba.ioc.spring.factory.SpringInstanceProvider;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;


/**
 * Integration testing base class. For managing persistence and IoC infrastructure
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager")
//@Transactional
@EnableTransactionManagement
public class AbstractIntegrationTest {
	
	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig()
	        .setApplyAllHighRepJobPolicy());


    @Inject
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        SpringInstanceProvider provider = new SpringInstanceProvider(applicationContext);
        InstanceFactory.setInstanceProvider(provider);
        
        helper.setUp();        
    }

    @After
    public void tearDown() throws Exception {
        InstanceFactory.setInstanceProvider(null);
        
        helper.tearDown();
    }
    
 // run this test twice to prove we're not leaking any state across tests
    /*   private void doTest() {
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        assertEquals(0, ds.prepare(new Query("yam")).countEntities(withLimit(10)));
        ds.put(new Entity("yam"));
        ds.put(new Entity("yam"));
        assertEquals(2, ds.prepare(new Query("yam")).countEntities(withLimit(10)));
    }

   @Test
    public void testInsert1() {
        doTest();
    }

       @Test
    public void testInsert2() {
        doTest();
    }*/
    	
    	  @Test
    	    public void test() {

    	    }

}