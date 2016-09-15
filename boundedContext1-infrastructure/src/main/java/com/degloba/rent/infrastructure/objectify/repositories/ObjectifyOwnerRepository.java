package com.degloba.rent.infrastructure.objectify.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.objectify.GenericDaoImpl;
import com.degloba.rent.domain.objectify.IOwnerRepositoryObjectify;

import java.io.Serializable;
import java.util.logging.Logger;


/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class ObjectifyOwnerRepository extends GenericDaoImpl implements IOwnerRepositoryObjectify, Serializable{
//////public class ObjectifyCategoryRepository extends AbstractGenericDaoImpl implements IGenericRepositoryObjectify{

	private final static Logger logger = Logger.getLogger(ObjectifyOwnerRepository.class.getName());
	
		
}
