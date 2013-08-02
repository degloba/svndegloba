package com.insacosa.presentation.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import query.annotations.Finder;

import com.insacosa.domain.InmobleCaract;
import com.insacosa.domain.Solicituds;
import com.insacosa.presentation.InmoblesFinder;
import com.insacosa.presentation.SolicitudsListItemDto;

import com.insacosa.vo.FotoForm;
import com.insacosa.webui.InmobleItemDto;
import com.insacosa.webui.UsuariItemDto;

/**
 * @author Rafał Jamróz
 */
@Finder
public class JpaInmoblesFinder implements InmoblesFinder {

    @PersistenceContext
    private EntityManager entityManager;

  
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
	public List<InmobleItemDto> findInmobles() {
		Query query = entityManager
                .createQuery("select new com.insacosa.presentation.InmobleItemDto("
                        + "o.id, o.totalCost, o.submitDate, o.status) from Inmobles o");
        return query.getResultList();
	}

	@Override
	public List<InmobleItemDto> inmoblesSolicitatsPerUsuari(Long usuariId)
	{
	return null;
	
	}


	@Override
	public InmobleItemDto detallInmoble(String keyInmoble) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<InmobleItemDto> inmoblesVenedorRang(UsuariItemDto usuari,
			int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public InmobleCaract valorsCaracteristiquesInmoble(Object inmobleKey) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<InmobleItemDto> inmoblesVenedor(UsuariItemDto usuari) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
