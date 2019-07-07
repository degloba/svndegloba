package com.degloba.lloguers.infrastructure.persistence.rdbms.jpa.repositories;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.lloguers.domain.persistence.rdbms.jpa.Categoria;
import com.degloba.lloguers.domain.persistence.rdbms.jpa.ILloguersRepository;
import com.degloba.lloguers.domain.persistence.rdbms.jpa.Foto;
import com.degloba.persistence.rdbms.jpa.EntityRepository;

/**
 * @category Repositori + JPA : Lloguer
 * 
 */
@DomainRepositoryImpl
public class LloguerRepository extends EntityRepository implements ILloguersRepository{
	
private final static Logger logger = Logger.getLogger(LloguerRepository.class.getName());
	
	private EntityManager em;

	@PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactoryDatastore")	
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Categoria loadCategory(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCategory(Categoria category) {
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
	public List<Categoria> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Foto loadPhoto(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardaFoto(Foto photo) {
		// TODO Auto-generated method stub
		try {
			
		em.persist(photo);
		} catch(Exception e) {
            //log it or do something
			//throw new AppException("DB exception", e)
			logger.warning(e.getMessage());			
        }

	}

		

/*	@Override
	public void saveSubcategory(Subcategory subcategory) {
		// TODO Auto-generated method stub
		try {
			
		em.persist(subcategory);
		} catch(Exception e) {
            //log it or do something
			//throw new AppException("DB exception", e)
			logger.warning(e.getMessage());			
        }

	}*/



}
