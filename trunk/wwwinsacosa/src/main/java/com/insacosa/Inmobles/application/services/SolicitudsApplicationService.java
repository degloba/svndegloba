package com.insacosa.Inmobles.application.services;

import javax.inject.Inject;

import com.insacosa.Inmobles.domain.Solicituds;

import application.ApplicationEventPublisher;
import application.SystemUser;
import application.annotation.ApplicationService;

import com.insacosa.Inmobles.domain.errors.OrderCreationException;
import com.insacosa.Inmobles.domain.repositories.SolicitudsRepository;

@ApplicationService
public class SolicitudsApplicationService<T extends Solicituds> extends GenericApplicationServiceForBaseEntity <Long,T>
	{
	
		@Inject
		private SolicitudsRepository solicitudsRepository;

	    @Inject
	    private SystemUser systemUser;

	    @Inject
	    private ApplicationEventPublisher eventPublisher;


	    GenericApplicationServiceForBaseEntity<Long,Solicituds>  g;
	    
	    
	    private void GetSolicitudsById(Long id) {
	    	
	    	//ds.CreateService().Get(id);
	    	
	    }
	    
	    

	public void saveSolicitud(Long solicitudId) {
		
		
	    // if we want to Spy Clients:)
	   /////////////// eventPublisher.publish(new ProductAddedToOrderEvent(product.getEntityId(), systemUser.getUserId(), quantity));
	  
	}
	
    public void createNewSolicitud() throws OrderCreationException {
/////////////Client client = loadClient(systemUser.getUserId());
/////////////Solicituds solicitud = orderFactory.crateOrder(client);
/////////////solicitudsRepository.persist(solicitud);
    }
	
    
	public void eliminarSolicitud(Long id) {
		
		
	}



	public SolicitudsRepository getSolicitudsRepository() {
		return solicitudsRepository;
	}



	public void setSolicitudsRepository(SolicitudsRepository solicitudsRepository) {
		this.solicitudsRepository = solicitudsRepository;
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
