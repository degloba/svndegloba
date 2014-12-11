package com.insacosa.Inmobles.application.services;

import javax.inject.Inject;

// Domini
import com.insacosa.Inmobles.domain.Provincies;
import com.insacosa.Inmobles.domain.repositories.InmoblesRepository;
import com.insacosa.Inmobles.domain.repositories.ProvinciesRepository;

// DDD
import application.ApplicationEventPublisher;
import application.SystemUser;
import application.annotation.ApplicationService;


@ApplicationService
public class ProvinciesApplicationService<T extends Provincies> extends GenericApplicationServiceForBaseEntity<Long,T> {
	
    	@Inject
    	private ProvinciesRepository provinciesRepository;
    
	    @Inject
	    private SystemUser systemUser;

	    @Inject
	    private ApplicationEventPublisher eventPublisher;
	    
	    
	    GenericApplicationServiceForBaseEntity<Long,Provincies>  g;
	    
	    
	    private void GetProvinciesById(Long id) {
	    	
	    	//g.ds.CreateService().Get(id);
	    	
	    }

		public void updateClasseApp(Provincies provincia) {
			// TODO Auto-generated method stub
			
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

		public ProvinciesRepository getProvinciesRepository() {
			return provinciesRepository;
		}

		public void setProvinciesRepository(ProvinciesRepository provinciesRepository) {
			this.provinciesRepository = provinciesRepository;
		}


		
	    

	

	

}