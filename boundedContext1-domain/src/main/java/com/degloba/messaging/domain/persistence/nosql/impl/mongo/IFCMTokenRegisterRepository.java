package com.degloba.domain.messaging.persistence.nosql.impl.mongo;

import com.degloba.domain.annotations.DomainRepository;

/** 
*
* @category Repositori (MongoDB) : Registre Tokens Firebase Cloud Message
* 
* @author pere
*
**/ 
@DomainRepository
public interface IFCMTokenRegisterRepository {

	public void logAllFCMTokenRegister();  		 	
	public void createFCMTokenRegisterCollection(); 
	public void dropFCMTokenRegisterCollection(); 
	public void insertFCMTokenRegister(String token); 
	
}
