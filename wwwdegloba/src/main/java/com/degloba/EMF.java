package com.degloba;


import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EMF {  

	private static final EntityManagerFactory emfInstance =  Persistence.createEntityManagerFactory("transactions-optional", new Properties());      
	
	private EMF() {}
	
public static EntityManagerFactory get() {    
	
	return emfInstance;    
	}
}
