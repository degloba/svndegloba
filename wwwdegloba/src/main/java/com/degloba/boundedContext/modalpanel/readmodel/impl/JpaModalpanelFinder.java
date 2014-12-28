package com.degloba.boundedContext.modalpanel.readmodel.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;




// JPA 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;

import query.PaginatedResult;

// Entitat Domini




import com.degloba.boundedContext.modalpanel.domain.Modalpanel;
import com.degloba.boundedContext.modalpanel.readmodel.IModalpanelFinder;
import com.degloba.boundedContext.modalpanel.readmodel.ModalpanelDto;
import com.degloba.boundedContext.modalpanel.readmodel.ModalpanelQuery;
// 
import com.degloba.boundedContext.ui.webui.jsf.ModalPanelJSFBean;




// DDD
import domain.annotations.FinderImpl;
import domain.support.IDomainEvent;
import domain.support.IDomainEventPublisher;


@FinderImpl
public class JpaModalpanelFinder implements IModalpanelFinder {

	@PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactory")
	private EntityManager entityManager;
	
	private static final Logger log = Logger.getLogger(ModalPanelJSFBean.class.getName());

/*	@SuppressWarnings("unchecked")
	@Override
	public List<OfferedProductDto> find(OfferQuery query) {
		@SuppressWarnings("unused")
		boolean bestBeforeExpired = query.isBestBeforeExpired();
		// TODO take into consideration in query

		return (List<OfferedProductDto>) entityManager
				.createQuery(
						"SELECT NEW pl.com.bottega.ecommerce.sales.readmodel.offer.ProductDto(p.aggregateId) FROM Product p")
				.getResultList();
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Modalpanel> findAll() {
		
		List<Modalpanel> ret = new ArrayList<Modalpanel>();

		//EntityTransaction tx = entityManager.getTransaction();
		//tx.begin();
		
		try {      
			
			Query q = entityManager.createQuery("select c FROM com.degloba.boundedContext.domain.modalpanel.Modalpanel c");
			
			ret = (List<Modalpanel>)q.getResultList();
			/*PersistenceUnitUtil util=entityManager.getEntityManagerFactory().getPersistenceUnitUtil();
			Object projectId = util.getIdentifier(ret.get(0));*/
			
			ret.size();	
			IDomainEventPublisher<?> de =ret.get(0).getDomainEventPublisher();
						    
		} catch (RuntimeException e) {
			log.warning("Modalpanel error" + e.getMessage() );
		    //if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}

		return ret;

	}

	@Override
	public PaginatedResult<ModalpanelDto> query(ModalpanelQuery<?> orderQuery) {
		// TODO Auto-generated method stub
		return null;
	}


}