package com.insacosa.presentation.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import javax.persistence.criteria.Root;


import query.annotations.Finder;

import com.insacosa.domain.Inmobles;
import com.insacosa.domain.Tipus;
import com.insacosa.domain.Usuaris;
import com.insacosa.presentation.TipusDto;
import com.insacosa.presentation.UsuarisFinder;

import com.insacosa.webui.UsuariItemDto;

/**
 * @author Rafał Jamróz
 */
@Finder
public class JpaUsuarisFinder implements UsuarisFinder {

    @PersistenceContext
    private EntityManager entityManager;

    public TipusDto getClientOrderDetails(Long orderId) {
        Tipus tipus = entityManager.find(Tipus.class, orderId);
        return tipus == null ? null : toSolicitudsListItemDto(tipus);
    }

    private TipusDto toSolicitudsListItemDto(Tipus tipus) {
    	TipusDto dto = new TipusDto();
        dto.setOrderId(tipus.getEntityId());
/*        dto.setTotalCost(solicitud.getTotalCost());
        dto.setOrderedProducts(solicitud.getOrderedProducts());
        dto.setSubmitDate(solicitud.getSubmitDate());
        dto.setOrderStatus(solicitud.getStatus());*/
        return dto;
    }


	@Override
	public List<UsuariItemDto> findUsuaris() {
		Query query = entityManager
                .createQuery("select new com.insacosa.presentation.UsuarisItemDto("
                        + "o.id, o.totalCost, o.submitDate, o.status) from Usuaris o");
        return query.getResultList();
	}


	@Override
	public UsuariItemDto cercarUsuari(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuaris usuariValid(Usuaris usuari) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuaris> solicitantsInmoble(Inmobles inmoble) {
		
		
		List<Usuaris> nomsUsuaris = new ArrayList<Usuaris>();
		List<Usuaris> ret = new ArrayList<Usuaris>();
		
		EntityTransaction tx = entityManager.getTransaction();
		
		try {      
			
			/*
			//Noms Usuaris solicitants d'un determinat inmoble
			String hql = "select usuaris.nomusuari from Solicituds where inmobles = :inmoble";
			Query query = session.createQuery(hql);
			query.setEntity("inmoble", inmoble); 
			
			nomsUsuaris = query.list();
			
			if (!nomsUsuaris.isEmpty())
			{
				hql = "from Usuaris where nomusuari in (:names)";
				query = session.createQuery(hql); 
				query.setParameterList("names", nomsUsuaris);
				ret = query.list();
			}
			*/
			
			
			
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Usuaris> crit = cb.createQuery(Usuaris.class);
			Root<Usuaris> candidateRoot = crit.from(Usuaris.class);
			candidateRoot.alias("p");

			crit.select(candidateRoot);
			
			
			
			
			
			tx.commit();    
			 
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}
		
		return ret;
		
	}


    
}
