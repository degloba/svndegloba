package com.degloba.modalpanel.readmodel.impl;

import java.util.ArrayList;
import java.util.List;


// JPA 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;

import query.PaginatedResult;

// Entitat Domini

// 
//import com.degloba.boundedContext.ui.webui.jsf.ModalPanelJSFBean;

import com.degloba.annotations.FinderImpl;
import com.degloba.casino.modalpanel.Modalpanel;

// DDD

import com.degloba.domain.IDomainEventPublisher;
import com.degloba.modalpanel.readmodel.IModalpanelFinder;
import com.degloba.modalpanel.readmodel.ModalpanelDto;
import com.degloba.modalpanel.readmodel.ModalpanelQuery;


@FinderImpl
public class JpaModalpanelFinder implements IModalpanelFinder {

	@PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactory")
	private EntityManager entityManager;
	
	//private static final Logger log = Logger.getLogger(ModalPanelJSFBean.class.getName());

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
			//log.warning("Modalpanel error" + e.getMessage() );
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