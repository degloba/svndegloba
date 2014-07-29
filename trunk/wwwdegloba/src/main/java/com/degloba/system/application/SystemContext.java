package com.degloba.system.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import domain.canonicalmodel.publishedlanguage.AggregateId;

/*
 * Annotating a class with the @Configuration indicates that the class can be used 
 * by the Spring IoC container as a source of bean definitions. 
 */

@Configuration
public class SystemContext {      
	
	/*
	 * The @Bean annotation tells Spring that a method annotated with @Bean will return an object 
	 * that should be registered as a bean in the Spring application context
	 */
	@Bean        
	@Scope("prototype")        
	public SystemUser getSystemUser(){                
		return new SystemUser(new AggregateId("1"));//TODO introduce security integration        
		}
}

