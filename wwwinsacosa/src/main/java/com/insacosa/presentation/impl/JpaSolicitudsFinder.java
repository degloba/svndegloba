package com.insacosa.presentation.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import query.annotations.Finder;
import com.insacosa.domain.Solicituds;
import com.insacosa.presentation.SolicitudsListItemDto;

import com.insacosa.presentation.SolicitudsFinder;

/**
 * @author Rafał Jamróz
 */
@Finder
public class JpaSolicitudsFinder implements SolicitudsFinder {

    @PersistenceContext
    private EntityManager entityManager;

    public SolicitudsListItemDto getClientOrderDetails(Long orderId) {
        Solicituds solicitud = entityManager.find(Solicituds.class, orderId);
        return solicitud == null ? null : toSolicitudsListItemDto(solicitud);
    }

    private SolicitudsListItemDto toSolicitudsListItemDto(Solicituds solicitud) {
    	SolicitudsListItemDto dto = new SolicitudsListItemDto();
        dto.setOrderId(solicitud.getEntityId());
/*        dto.setTotalCost(solicitud.getTotalCost());
        dto.setOrderedProducts(solicitud.getOrderedProducts());
        dto.setSubmitDate(solicitud.getSubmitDate());
        dto.setOrderStatus(solicitud.getStatus());*/
        return dto;
    }


	@Override
	public List<SolicitudsListItemDto> findSolicituds() {
		Query query = entityManager
                .createQuery("select new com.insacosa.presentation.SolicitudsListItemDto("
                        + "o.id, o.totalCost, o.submitDate, o.status) from Solicituds o");
        return query.getResultList();
	}

	@Override
	public SolicitudsListItemDto getSolicitudsClient(Long clientId) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
