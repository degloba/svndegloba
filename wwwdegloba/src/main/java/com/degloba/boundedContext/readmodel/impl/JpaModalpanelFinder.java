package com.degloba.boundedContext.readmodel.impl;

/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


// JPA - Persistencia
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



// CQRS
import query.annotations.Finder;



// Entitat Domini
import com.degloba.boundedContext.domain.Modalpanel;

// 
import com.degloba.boundedContext.readmodel.ModalpanelsFinder;
import com.degloba.boundedContext.webui.JSF.ModalPanels;

import domain.annotations.FinderImpl;


@FinderImpl
public class JpaModalpanelFinder implements ModalpanelsFinder {

	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger log = Logger.getLogger(ModalPanels.class.getName());

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
			
			Query q = entityManager.createQuery("select c FROM Modalpanel c");
			
			ret = (List<Modalpanel>)q.getResultList();
			ret.size();
			    
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


}