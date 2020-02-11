package com.degloba.gcm.domain.persistence.nosql.mongo.spring;

import com.degloba.domain.annotations.DomainRepository;

/** 
*
* Repositori (MongoDB) : Registre Tokens GCM
* 
* @author pere
*
**/ 
@DomainRepository
public interface IGCMTokenRegisterRepository {

	public void logAllGCMTokenRegister();  		 	
	public void createGCMTokenRegisterCollection(); 
	public void dropGCMTokenRegisterCollection(); 
	public void insertGCMTokenRegister(String token); 
	
}
