package com.insacosa.application.services;

import javax.inject.Inject;

import com.insacosa.domain.OrderRepository;
import com.insacosa.domain.ProductRepository;
import com.insacosa.domain.InvoiceRepository;

import com.insacosa.domain.InvoicingService;

import com.insacosa.domain.OrderFactory;


import ddd.application.ApplicationEventPublisher;
import ddd.application.SystemUser;
import ddd.application.annotation.ApplicationService;

import com.insacosa.domain.*;


@ApplicationService
public class TipusApplicationService<T extends Tipus> extends GenericApplicationServiceForBaseEntity <Long,T>

	 {


	    @Inject
	    private SystemUser systemUser;

	    @Inject
	    private ApplicationEventPublisher eventPublisher;


	public Tipus tipusInmoble(String keyInmoble) {
		// TODO Auto-generated method stub
		return null;
	}


	public SystemUser getSystemUser() {
		return systemUser;
	}


	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}


	public ApplicationEventPublisher getEventPublisher() {
		return eventPublisher;
	}


	public void setEventPublisher(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}
	
	

}
