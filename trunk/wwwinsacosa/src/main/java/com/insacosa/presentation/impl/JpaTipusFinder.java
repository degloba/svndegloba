package com.insacosa.presentation.impl;

import java.util.List;

// JPA
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import query.annotations.Finder;

import com.insacosa.domain.Caracteristiques;
import com.insacosa.domain.Inmobles;

import com.insacosa.domain.Tipus;
import com.insacosa.presentation.TipusDto;
import com.insacosa.presentation.TipusFinder;


/**
 * @author Rafał Jamróz
 */
@Finder
public class JpaTipusFinder implements TipusFinder {

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


	public List<TipusDto> findTipus() {
		Query query = entityManager
                .createQuery("select new com.insacosa.presentation.SolicitudsListItemDto("
                        + "o.id, o.totalCost, o.submitDate, o.status) from Tipus o");
        return query.getResultList();
	}

   

	public TipusDto tipusInmoble(String keyInmoble) {
		
		TipusDto ret = null;
		
		
		try {  
			
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	        CriteriaQuery<Tipus> q = cb.createQuery(Tipus.class);
	        Root<Inmobles> root = q.from(Inmobles.class);
	        
	        Path<String> path = root.get("id"); 
	        Predicate p = cb.equal(path, keyInmoble);
	        q.where(p);
	        
	        Selection<Tipus> s = root.get("Tipus");
	        q.select(s);
					
			TypedQuery<Tipus> tq = entityManager.createQuery(q);
			//ret = tq.getSingleResult();
			
			}
		finally {
		    //em.close();
		}
		
		return ret;
		
	}

	public List<Caracteristiques> caractTipus(Tipus tipus, int i,
			boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

    
}
