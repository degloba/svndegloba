package com.degloba.rent.infrastructure.persistence.nosql.googleDatastore.api.objectify.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.nosql.googleDatastore.api.objectify.BaseRepository;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.ICategoryRepository;

import java.util.logging.Logger;



/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class CategoryRepository extends BaseRepository implements ICategoryRepository{

	private final static Logger logger = Logger.getLogger(CategoryRepository.class.getName());
	
		
}
