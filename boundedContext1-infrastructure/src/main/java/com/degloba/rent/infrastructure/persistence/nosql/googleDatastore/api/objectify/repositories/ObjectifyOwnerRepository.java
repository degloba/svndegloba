package com.degloba.rent.infrastructure.persistence.nosql.googleDatastore.api.objectify.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.nosql.googleDatastore.api.objectify.BaseRepository;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.IOwnerRepositoryObjectify;

import java.io.Serializable;
import java.util.logging.Logger;


/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class ObjectifyOwnerRepository extends BaseRepository implements IOwnerRepositoryObjectify, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(ObjectifyOwnerRepository.class.getName());
	
		
}
