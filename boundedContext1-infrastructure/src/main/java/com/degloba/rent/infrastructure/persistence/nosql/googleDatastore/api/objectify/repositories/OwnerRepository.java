package com.degloba.rent.infrastructure.persistence.nosql.googleDatastore.api.objectify.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.nosql.googleDatastore.api.objectify.BaseRepository;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.IOwnerRepository;

import java.io.Serializable;
import java.util.logging.Logger;


/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class OwnerRepository extends BaseRepository implements IOwnerRepository, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(OwnerRepository.class.getName());
	
		
}
