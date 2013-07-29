package com.insacosa.application.services;

import javax.inject.Inject;


import com.insacosa.domain.InvoiceRepository;
import com.insacosa.domain.InvoicingService;
import com.insacosa.domain.OrderFactory;
import com.insacosa.domain.OrderRepository;
import com.insacosa.domain.ProductRepository;

import ddd.application.ApplicationEventPublisher;
import ddd.application.SystemUser;
import ddd.application.annotation.ApplicationService;


@ApplicationService
public class ProvinciesApplicationService {
	
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


	

	

}
