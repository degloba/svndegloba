package com.insacosa.application.services;

import javax.inject.Inject;

import com.insacosa.application.events.ProductAddedToOrderEvent;
import com.insacosa.domain.Client;
import com.insacosa.domain.InvoiceRepository;
import com.insacosa.domain.InvoicingService;
import com.insacosa.domain.Order;
import com.insacosa.domain.OrderFactory;
import com.insacosa.domain.OrderRepository;
import com.insacosa.domain.Product;
import com.insacosa.domain.ProductRepository;


import ddd.application.ApplicationEventPublisher;
import ddd.application.SystemUser;
import ddd.application.annotation.ApplicationService;


import com.insacosa.domain.*;
import com.insacosa.domain.errors.OrderCreationException;
import com.insacosa.domain.repositories.SolicitudsRepository;

@ApplicationService
public class SolicitudsApplicationService 
	{
	
	 	@Inject
	    private SolicitudsRepository solicitudsRepository;
	
	    @Inject
	    private OrderFactory orderFactory;

	    @Inject
	    private InvoiceRepository invoiceRepository;

	    @Inject
	    private InvoicingService invoicingService;  // exemple Servei de Domini

	    @Inject
	    private SystemUser systemUser;

	    @Inject
	    private ApplicationEventPublisher eventPublisher;



	public void saveSolicitud(Long solicitudId) {
		
		Solicituds solicitud = solicitudsRepository.load(solicitudId);
	      
		// Domain logic
		solicitudsRepository.save(solicitud);

	    // if we want to Spy Clients:)
	   /////////////// eventPublisher.publish(new ProductAddedToOrderEvent(product.getEntityId(), systemUser.getUserId(), quantity));
	  
	}
	
    public void createNewSolicitud() throws OrderCreationException {
/////////////Client client = loadClient(systemUser.getUserId());
/////////////Solicituds solicitud = orderFactory.crateOrder(client);
/////////////solicitudsRepository.persist(solicitud);
    }
	

}
