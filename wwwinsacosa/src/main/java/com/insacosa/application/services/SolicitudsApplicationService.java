package com.insacosa.application.services;

import javax.inject.Inject;


import com.insacosa.domain.Inmobles;
import com.insacosa.domain.InvoiceRepository;
import com.insacosa.domain.InvoicingService;

import com.insacosa.domain.Solicituds;

import com.insacosa.domain.OrderFactory;

import ddd.application.ApplicationEventPublisher;
import ddd.application.SystemUser;
import ddd.application.annotation.ApplicationService;

import com.insacosa.domain.errors.OrderCreationException;

import com.insacosa.domain.repositories.SolicitudsRepository;

@ApplicationService
public class SolicitudsApplicationService<T extends Solicituds> extends GenericApplicationServiceForBaseEntity <Long,T>
	{
	
	    @Inject
	    private InvoicingService invoicingService;  // exemple Servei de Domini

	    @Inject
	    private SystemUser systemUser;

	    @Inject
	    private ApplicationEventPublisher eventPublisher;


	    GenericApplicationServiceForBaseEntity<Long,Solicituds>  g;
	    
	    
	    private void GetSolicitudsById(Long id) {
	    	
	    	g.ds.CreateService().Get(id);
	    	
	    }
	    
	    

	public void saveSolicitud(Long solicitudId) {
		
		
	    // if we want to Spy Clients:)
	   /////////////// eventPublisher.publish(new ProductAddedToOrderEvent(product.getEntityId(), systemUser.getUserId(), quantity));
	  
	}
	
    public void createNewSolicitud() throws OrderCreationException {
/////////////Client client = loadClient(systemUser.getUserId());
/////////////Solicituds solicitud = orderFactory.crateOrder(client);
/////////////solicitudsRepository.persist(solicitud);
    }
	
    
	public void eliminarSolicitud(Long id) {
		
		
	}

}
