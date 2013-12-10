package com.insacosa.application.services;


import javax.inject.Inject;

import application.ApplicationEventPublisher;
import application.SystemUser;
import application.annotation.ApplicationService;

import com.insacosa.domain.*;
import com.insacosa.domain.repositories.*;

/**
 * @author degloba
 *
 * @category Defineix el Servei d'Aplicació per l'entitat de Domini "Caracteristiques"
 */
@ApplicationService
public class CaracteristiquesApplicationService<T extends Caracteristiques> extends GenericApplicationServiceForBaseEntity<Long,T> 

 {
		
    @Inject
    private CaracteristiquesRepository caracteristiquesRepository;

    @Inject
    private SystemUser systemUser;

    @Inject
    private ApplicationEventPublisher eventPublisher;
    
    
    GenericApplicationServiceForBaseEntity<Long,Caracteristiques>  g;
    
    private void GetCaracteristiquesById(Long id) {
    	
    	g.ds.CreateService().Get(id);
    	
    }

	public CaracteristiquesRepository getCaracteristiquesRepository() {
		return caracteristiquesRepository;
	}

	public void setCaracteristiquesRepository(
			CaracteristiquesRepository caracteristiquesRepository) {
		this.caracteristiquesRepository = caracteristiquesRepository;
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
