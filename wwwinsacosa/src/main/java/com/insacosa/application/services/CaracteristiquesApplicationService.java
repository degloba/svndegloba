package com.insacosa.application.services;


import javax.inject.Inject;

import ddd.application.ApplicationEventPublisher;
import ddd.application.SystemUser;
import ddd.application.annotation.ApplicationService;

import com.insacosa.domain.*;
import com.insacosa.domain.repositories.CaracteristiquesRepository;

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
    
    
    GenericApplicationServiceForBaseEntity<Long,Caracteristiques>  g;
    
    private void GetCaracteristiquesById(Long id) {
    	
    	g.ds.CreateService().Get(id);
    	
    }


}
