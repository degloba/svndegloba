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
// JPA
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.SystemException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager")
//@Transactional
@EnableTransactionManagement
public class AbstractIntegrationTest {

    //private static BtmUtils btmUtils;

    //private static EntityManagerFactory emf;

    protected EntityManager entityManager;

    //private EntityTransaction tx;

    protected EntityRepository repository;
    
    @Inject
    private ApplicationContext applicationContext;
    
	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig()
	        .setApplyAllHighRepJobPolicy());

    @BeforeClass
    public static void setUpClass() throws Exception {
        //btmUtils = BtmUtils.readConfigurationFromClasspath("/datasources.properties");
        //btmUtils.setupDataSource();
        //emf = Persistence.createEntityManagerFactory("transactions-optional");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        //emf.close();
        //btmUtils.closeDataSource();
        //btmUtils = null;
        //System.out.println("================================================");
        //System.out.println("Close BTM");
    }

    @Before
    public void setUp() {
        SpringInstanceProvider provider = new SpringInstanceProvider(applicationContext);
        InstanceFactory.setInstanceProvider(provider);
        
    	helper.setUp();  
    	
        /*InstanceFactory.bind(EntityManagerFactory.class, emf);
        repository = new EntityRepositoryJpa(emf);
        AbstractEntity.setRepository(repository);
        entityManager = repository.getEntityManager();
        tx = entityManager.getTransaction();
        tx.begin();*/
    }

    @After
    public void tearDown() throws IllegalStateException, SystemException {
        /*tx.rollback();
        entityManager.close();
        repository = null;
        BaseAggregateRoot.setRepository(null);
        */
    	InstanceFactory.setInstanceProvider(null);
    	
        helper.tearDown();
    }
}
