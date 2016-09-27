package com.degloba.rent.infrastructure.objectify.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;

import com.degloba.objectify.BaseRepositoryObjectify;
import com.degloba.rent.domain.objectify.ICategoryRepositoryObjectify;

import java.util.logging.Logger;



/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class ObjectifyCategoryRepository extends BaseRepositoryObjectify implements ICategoryRepositoryObjectify{

	private final static Logger logger = Logger.getLogger(ObjectifyCategoryRepository.class.getName());
	
		
}
