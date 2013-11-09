package com.insacosa.application.services;

import javax.inject.Inject;

// Domini
import com.insacosa.domain.InvoiceRepository;
import com.insacosa.domain.InvoicingService;
import com.insacosa.domain.OrderFactory;
import com.insacosa.domain.OrderRepository;
import com.insacosa.domain.ProductRepository;
import com.insacosa.domain.Provincies;
import com.insacosa.domain.repositories.InmoblesRepository;
import com.insacosa.domain.repositories.ProvinciesRepository;

// DDD
import ddd.application.ApplicationEventPublisher;
import ddd.application.SystemUser;
import ddd.application.annotation.ApplicationService;


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
