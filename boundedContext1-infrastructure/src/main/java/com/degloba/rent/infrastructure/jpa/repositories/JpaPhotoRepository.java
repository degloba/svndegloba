package com.degloba.rent.infrastructure.jpa.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;

// Repository
import com.degloba.domain.JpaEntityRepository;

// Domain
import com.degloba.rent.domain.IPhotoRepository;
import com.degloba.rent.domain.Photo;

// Google App Engine
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class JpaPhotoRepository extends JpaEntityRepository<Photo> implements IPhotoRepository{

	private final static Logger logger = Logger.getLogger(JpaPhotoRepository.class.getName());
	
	private EntityManager em;

	@PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactoryDatastore")	
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Photo load(Key id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Photo photo) {
		// TODO Auto-generated method stub
		try {
			
		em.persist(photo);
		} catch(Exception e) {
            //log it or do something
			//throw new AppException("DB exception", e)
			logger.warning(e.getMessage());			
        }

	}

	
}
