package com.insacosa.application.services;


import javax.inject.Inject;

import ddd.application.ApplicationEventPublisher;
import ddd.application.SystemUser;
import ddd.application.annotation.ApplicationService;

import com.insacosa.domain.*;

@ApplicationService
public class CaracteristiquesApplicationService extends BaseApplicationService

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



}
