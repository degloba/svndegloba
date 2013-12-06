package com.insacosa.application.services;

import javax.inject.Inject;

import com.insacosa.domain.Ciutats;
import com.insacosa.domain.repositories.CiutatsRepository;

import ddd.application.ApplicationEventPublisher;
import ddd.application.SystemUser;
import ddd.application.annotation.ApplicationService;

/**
 * @author degloba
 *
 * @category Defineix el Servei d'Aplicació per l'entitat de Domini "Ciutats"
 */
@ApplicationService
public class CiutatsApplicationService<T extends Ciutats> extends  GenericApplicationServiceForBaseEntity<Long,T>
	 {
	
    @Inject
    private CiutatsRepository ciutatsRepository;

    @Inject
    private SystemUser systemUser;

    @Inject
    private ApplicationEventPublisher eventPublisher;
    

	public CiutatsRepository getCiutatsRepository() {
		return ciutatsRepository;
	}

	public void setCiutatsRepository(CiutatsRepository ciutatsRepository) {
		this.ciutatsRepository = ciutatsRepository;
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
