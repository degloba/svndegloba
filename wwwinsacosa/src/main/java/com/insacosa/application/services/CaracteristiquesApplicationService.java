package com.insacosa.application.services;

import java.util.List;

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



	public List<Caracteristiques> caractTipus(Tipus tipus) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Caracteristiques> caractTipus(Tipus tipus, Integer condicio, Boolean inclouCaractComu) {
		// TODO Auto-generated method stub
		return null;
	}

	public Caracteristiques caractPerKey(String idCaract) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Caracteristiques> allCaract() {
		// TODO Auto-generated method stub
		return null;
	}

	public Caracteristiques caracteristicaCaractInmoble(
			Caractinmobles caractinmoble) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
