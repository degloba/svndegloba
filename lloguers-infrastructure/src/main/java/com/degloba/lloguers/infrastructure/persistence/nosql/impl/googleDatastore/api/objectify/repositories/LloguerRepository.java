package com.degloba.lloguers.infrastructure.persistence.nosql.impl.googleDatastore.api.objectify.repositories;


import java.io.Serializable;

import java.util.logging.Logger;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.ILloguerRepository;
//////////////import com.degloba.messaging.domain.persistence.nosql.impl.mongo.api.spring.usuaris.Person;
import com.degloba.persistence.nosql.impl.googleDatastore.api.objectify.BaseRepository;


/**
 * @category Repositori + Objectify : {@link Rent}s  
 */
@DomainRepositoryImpl
public class LloguerRepository extends BaseRepository implements ILloguerRepository, Serializable{

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(LloguerRepository.class.getName());
	
}
