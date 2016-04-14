package com.degloba.usuaris.domain;

import com.degloba.domain.annotations.DomainRepository;

@DomainRepository
public interface IGCMTokenRegisterRepository {

	public void logAllGCMTokenRegister();  		 	
	public void createGCMTokenRegisterCollection(); 
	public void dropGCMTokenRegisterCollection(); 
	public void insertGCMTokenRegister(String token); 
	
}
