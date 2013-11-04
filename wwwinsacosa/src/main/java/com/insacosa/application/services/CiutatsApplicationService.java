package com.insacosa.application.services;


import com.insacosa.domain.Ciutats;
import com.insacosa.domain.InvoiceRepository;
import com.insacosa.domain.InvoicingService;
import com.insacosa.domain.OrderFactory;
import com.insacosa.domain.OrderRepository;
import com.insacosa.domain.ProductRepository;


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
	
	
	

}
