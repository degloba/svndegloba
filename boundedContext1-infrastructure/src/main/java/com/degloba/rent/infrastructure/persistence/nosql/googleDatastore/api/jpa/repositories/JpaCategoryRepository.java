package com.degloba.rent.infrastructure.persistence.nosql.googleDatastore.api.jpa.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.nosql.googleDatastore.api.jpa.EntityRepository;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.jpa.Category;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.jpa.ICategoryRepository;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;

// Repository



// Google App Engine
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class JpaCategoryRepository extends EntityRepository<Category> implements ICategoryRepository{

	private final static Logger logger = Logger.getLogger(JpaCategoryRepository.class.getName());
	
	private EntityManager em;

	@PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactoryDatastore")	
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Category load(Key id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Category category) {
		// TODO Auto-generated method stub
		try {
			
		em.persist(category);
		} catch(Exception e) {
            //log it or do something
			//throw new AppException("DB exception", e)
			logger.warning(e.getMessage());			
        }

	}

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
