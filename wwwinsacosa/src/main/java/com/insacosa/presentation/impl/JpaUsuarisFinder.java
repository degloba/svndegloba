package com.insacosa.presentation.impl;

import java.util.List;

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

import com.insacosa.domain.Inmobles;
import com.insacosa.domain.Solicituds;
import com.insacosa.domain.Tipus;
import com.insacosa.domain.Usuaris;
import com.insacosa.presentation.SolicitudsListItemDto;
import com.insacosa.presentation.TipusDto;
import com.insacosa.presentation.TipusFinder;
import com.insacosa.presentation.UsuarisFinder;

import com.insacosa.presentation.SolicitudsFinder;
import com.insacosa.webui.InmobleItemDto;
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
	public List<UsuariItemDto> solicitantsInmoble(InmobleItemDto inmoble) {
		// TODO Auto-generated method stub
		return null;
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

   
	
	
    
}
