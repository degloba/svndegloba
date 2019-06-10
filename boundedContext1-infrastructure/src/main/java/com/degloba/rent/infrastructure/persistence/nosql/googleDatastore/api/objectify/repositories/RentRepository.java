package com.degloba.rent.infrastructure.persistence.nosql.googleDatastore.api.objectify.repositories;


import java.io.Serializable;

import java.util.logging.Logger;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.lloguer.domain.persistence.nosql.googleDatastore.api.objectify.ILloguerRepository;
import com.degloba.persistence.nosql.googleDatastore.api.objectify.BaseRepository;
import com.degloba.usuaris.domain.persistence.nosql.mongo.spring.Person;


/**
 * @category Repositori + Objectify : {@link Rent}s  
 */
@DomainRepositoryImpl
public class RentRepository extends BaseRepository implements ILloguerRepository, Serializable{

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(RentRepository.class.getName());
	
}
