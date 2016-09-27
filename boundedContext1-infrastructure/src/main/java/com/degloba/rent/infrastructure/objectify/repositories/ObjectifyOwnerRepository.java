package com.degloba.rent.infrastructure.objectify.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.objectify.BaseRepositoryObjectify;
import com.degloba.rent.domain.objectify.IOwnerRepositoryObjectify;

import java.io.Serializable;
import java.util.logging.Logger;


/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class ObjectifyOwnerRepository extends BaseRepositoryObjectify implements IOwnerRepositoryObjectify, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(ObjectifyOwnerRepository.class.getName());
	
		
}
