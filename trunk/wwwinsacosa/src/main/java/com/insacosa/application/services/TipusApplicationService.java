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

import com.insacosa.domain.*;

@ApplicationService
public class TipusApplicationService extends BaseApplicationService

	 {

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


	public Tipus tipusInmoble(String keyInmoble) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
