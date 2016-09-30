package com.degloba.rent.infrastructure.jpa.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.rdbms.jpa.JpaEntityRepository;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;

// Repository


// Domain
import com.degloba.rent.domain.jpa.IPhotoRepository;
import com.degloba.rent.domain.jpa.ISubcategoryRepository;
import com.degloba.rent.domain.jpa.Photo;
import com.degloba.rent.domain.jpa.Subcategory;
// Google App Engine
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class JpaSubcategoryRepository extends JpaEntityRepository<Subcategory> implements ISubcategoryRepository{

	private final static Logger logger = Logger.getLogger(JpaSubcategoryRepository.class.getName());
	
	private EntityManager em;

	@PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactoryDatastore")	
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Subcategory load(Key id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Subcategory subcategory) {
		// TODO Auto-generated method stub
		try {
			
		em.persist(subcategory);
		} catch(Exception e) {
            //log it or do something
			//throw new AppException("DB exception", e)
			logger.warning(e.getMessage());			
        }

	}

	@Override
	public List<Subcategory> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
