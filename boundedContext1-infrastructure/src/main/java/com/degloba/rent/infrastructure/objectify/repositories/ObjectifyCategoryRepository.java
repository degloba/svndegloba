package com.degloba.rent.infrastructure.objectify.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.nosql.googleDatastore.api.objectify.BaseRepository;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.ICategoryRepositoryObjectify;

import java.util.logging.Logger;



/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class ObjectifyCategoryRepository extends BaseRepository implements ICategoryRepositoryObjectify{

	private final static Logger logger = Logger.getLogger(ObjectifyCategoryRepository.class.getName());
	
		
}
