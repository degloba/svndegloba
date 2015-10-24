package com.degloba.travel.config.services;

import com.gemstone.gemfire.cache.Cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

//Spring
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// Domain
import com.degloba.travel.domain.Hotel;


import javax.sql.DataSource;
import java.sql.Driver;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple class that initializes all the services including data access logic
 */
@Import(IntegrationConfiguration.class)
@PropertySource("classpath:/META-INF/spring/ds.standalone.properties")
@ComponentScan("com.degloba.travel.services")
@EnableTransactionManagement
@Configuration
public class ServicesConfiguration {

    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private Environment environment;

    /*@Bean(name = "dataSource")
    @SuppressWarnings("unchecked")
    public DataSource dataSource() throws Exception {
        SimpleDriverDataSource driverManagerDataSource = new SimpleDriverDataSource();
        driverManagerDataSource.setUrl(this.environment.getProperty("ds.url"));
        driverManagerDataSource.setPassword(this.environment.getProperty("ds.password"));
        driverManagerDataSource.setUsername(this.environment.getProperty("ds.user"));
        driverManagerDataSource.setDriverClass((Class<Driver>) Class.forName(this.environment.getProperty("ds.driverClassName")));
        return driverManagerDataSource;
    }*/

    
   /************************************************************************************************************************************** 
    <bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalEntityManagerFactoryBean">
    	<property name="persistenceUnitName" value="transactions-optional" />          
	</bean>   

	<!-- <bean id="entityManagerFactoryMongo" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean" lazy-init="true">        
    	<property name="persistenceUnitName" value="mongodb" />
	</bean>  --> 

	<!-- TransactionManagerS -->
	<bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
		<property name="entityManagerFactory" ref="entityManagerFactory" />    
	</bean> 
   **************************************************************************************************************************************/ 
        
    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        return new JpaTransactionManager(this.entityManagerFactory().getObject());
    }
    
    
    
   	/*
   	 * <persistence-unit name="transactions-optional">        
   		<provider>org.datanucleus.api.jpa.PersistenceProviderImpl</provider>
   				
   		<properties>            
   			<property name="datanucleus.NontransactionalRead" value="true"/>            
   			<property name="datanucleus.NontransactionalWrite" value="true"/>            
   			<property name="datanucleus.ConnectionURL" value="appengine"/> 
   			<property name="datanucleus.appengine.datastoreEnableXGTransactions" value="true"/> 			
    		<property name="datanucleus.singletonEMFForName" value="true"/>	
    */
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
    	
       /* HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(true);  */

        Map<String, String> props = new HashMap<String, String>();

        // validate or create
        //props.put("hibernate.hbm2ddl.auto", "validate"); // it will attempt to run import.sql, by default! be carefyl

        if (log.isDebugEnabled()) {
            log.debug("the 'hibernate.hbm2ddl.auto' property was set to 'validate,' " +
                    "which means it will not attempt to run 'import.sql' by itself. " +
                    "You should manually run the statements in 'import.sql,' yourself " +
                    "to ensure correct operating conditions for the application.");
        }

        
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        //LocalEntityManagerFactoryBean emf = new LocalEntityManagerFactoryBean();
        
        // We want DataNucleus to recreate the database schema
        //props.put("datanucleus.autoCreateSchema", "true");
		  
        props.put("datanucleus.NontransactionalRead" ,"true");            
		props.put("datanucleus.NontransactionalWrite" ,"true");            
		props.put("datanucleus.ConnectionURL","appengine"); 
		props.put("datanucleus.appengine.datastoreEnableXGTransactions" ,"true"); 			
		props.put("datanucleus.singletonEMFForName" ,"true");	
		 		  
		emf.setPersistenceProviderClass(org.datanucleus.api.jpa.PersistenceProviderImpl.class);
		
        
        //LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        //em.setJpaVendorAdapter(jpaVendorAdapter);
        //em.setDataSource(dataSource());
        //em.setJpaPropertyMap(props);

        String entityPackage = Hotel.class.getPackage().getName();
        //emf.setPackagesToScan(entityPackage);

        // look ma, no persistence.xml !
        return emf;
    }


/*    @Autowired
    @Bean(name = "gemfireCacheManager")
    public CacheManager gemfireCacheManager(@Qualifier("c") Cache gemfireCache) {
        org.springframework.data.gemfire.support.GemfireCacheManager cacheManager = new org.springframework.data.gemfire.support.GemfireCacheManager();
        cacheManager.setCache(gemfireCache);
        return cacheManager;
    }*/

}
