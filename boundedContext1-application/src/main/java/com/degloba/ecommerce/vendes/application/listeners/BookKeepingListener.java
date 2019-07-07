package com.degloba.ecommerce.vendes.application.listeners;

import javax.inject.Inject;

// Events
import com.degloba.event.annotations.EventListeners;
import com.degloba.ecommerce.vendes.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.vendes.compres.domain.persistence.rdbms.jpa.Compra;
import com.degloba.ecommerce.vendes.domain.events.ComandaEnviadaEvent;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.IVendesRepository;
import com.degloba.ecommerce.vendes.facturacio.domain.factories.PeticionsFacturaFactory;
import com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa.Factura;
import com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa.PeticioFactura;
import com.degloba.ecommerce.vendes.facturacio.domain.services.BookKeeperService;
import com.degloba.ecommerce.vendes.facturacio.domain.services.AssesorFiscalService;
import com.degloba.event.annotations.EventListener;

/**
 * @category Listener d'events implementat amb la implementació d'events degloba/Spring
 * 
 * @author degloba
 *
 */
@EventListeners
public class BookKeepingListener {

	@Inject
	private BookKeeperService bookKeeper;
	
	@Inject
	private IVendesRepository vendesRepository;
		
	@Inject
	private AssesorFiscalService assesorFiscalService;
	
	
	@Inject
	private PeticionsFacturaFactory peticionsFacturaFactory;
	
	@EventListener
	public void handle(ComandaEnviadaEvent event){
		// recuperem la compra a partir de l'Id de l'ordre
		Compra compra = vendesRepository.get(Compra.class, event.getComandaId());
		
		// recuperem el {@link Client} a partir de {@link ClientData}
		Client client = vendesRepository.get(Client.class, compra.getClientData().getAggregateId());
		
		PeticioFactura request  = peticionsFacturaFactory.create(client, compra); 
		
		Factura factura = bookKeeper.emet(request, assesorFiscalService.suggereixMillorImpost(client));
		
		vendesRepository.save(factura);
	}
}
