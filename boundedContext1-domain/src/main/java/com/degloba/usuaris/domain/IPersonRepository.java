package com.degloba.usuaris.domain;

import com.degloba.domain.annotations.DomainRepository;

@DomainRepository
public interface IPersonRepository {

	public void logAllPersons();  		
	public void insertPersonWithNameJohnAndRandomAge(); 	
	public void createPersonCollection(); 
	public void dropPersonCollection(); 
	
}
