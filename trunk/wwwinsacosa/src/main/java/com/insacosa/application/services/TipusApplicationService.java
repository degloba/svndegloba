package com.insacosa.application.services;

import javax.inject.Inject;

import application.ApplicationEventPublisher;
import application.SystemUser;
import application.annotation.ApplicationService;

import com.insacosa.domain.*;
import com.insacosa.domain.repositories.TipusRepository;


@ApplicationService
public class TipusApplicationService<T extends Tipus> extends GenericApplicationServiceForBaseEntity <Long,T>

	 {

		@Inject
		private TipusRepository tipusRepository;

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


	public TipusRepository getTipusRepository() {
		return tipusRepository;
	}


	public void setTipusRepository(TipusRepository tipusRepository) {
		this.tipusRepository = tipusRepository;
	}
	
	

}
