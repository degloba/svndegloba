package com.insacosa.application.services;

import javax.inject.Inject;

import com.insacosa.domain.Caracteristiques;
import com.insacosa.domain.Inmobles;
import com.insacosa.domain.InvoiceRepository;
import com.insacosa.domain.InvoicingService;
import com.insacosa.domain.OrderFactory;
import com.insacosa.domain.OrderRepository;
import com.insacosa.domain.ProductRepository;
import com.insacosa.domain.Provincies;

import ddd.application.ApplicationEventPublisher;
import ddd.application.SystemUser;
import ddd.application.annotation.ApplicationService;


@ApplicationService
public class ProvinciesApplicationService<T extends Provincies> extends GenericApplicationServiceForBaseEntity<Long,T> {
	
	 	@Inject
	    private OrderRepository orderRepository;

	    @Inject
	    private OrderFactory orderFactory;

	    @Inject
	    private ProductRepository productRepository;

	    @Inject
	    private InvoiceRepository invoiceRepository;

	    @Inject
	    private InvoicingService invoicingService;

	    @Inject
	    private SystemUser systemUser;

	    @Inject
	    private ApplicationEventPublisher eventPublisher;
	    
	    
	    GenericApplicationServiceForBaseEntity<Long,Provincies>  g;
	    
	    
	    private void GetProvinciesById(Long id) {
	    	
	    	g.ds.CreateService().Get(id);
	    	
	    }

		public void updateClasseApp(Provincies provincia) {
			// TODO Auto-generated method stub
			
		}


		
	    

	

	

}
