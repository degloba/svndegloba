package com.degloba.rent.infrastructure.objectify.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.objectify.AbstractGenericDaoImpl;
import com.degloba.objectify.IGenericRepositoryObjectify;
import com.degloba.rent.domain.objectify.ICategoryRepositoryObjectify;

import java.util.logging.Logger;



/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class ObjectifyCategoryRepository extends AbstractGenericDaoImpl implements ICategoryRepositoryObjectify{
//////public class ObjectifyCategoryRepository extends AbstractGenericDaoImpl implements IGenericRepositoryObjectify{

	private final static Logger logger = Logger.getLogger(ObjectifyCategoryRepository.class.getName());
	
		
}
