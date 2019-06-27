package com.degloba.ecommerce.vendes.application.listeners;

import javax.inject.Inject;

// Events
import com.degloba.event.annotations.EventListeners;
import com.degloba.ecommerce.vendes.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.vendes.compres.domain.persistence.rdbms.jpa.Compra;
import com.degloba.ecommerce.vendes.domain.events.OrdreEnviadaEvent;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.IVendaRepository;
import com.degloba.ecommerce.vendes.facturacio.domain.factories.PeticioFacturaFactory;
import com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa.Factura;
import com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa.PeticioFactura;
import com.degloba.ecommerce.vendes.facturacio.domain.services.BookKeeperService;
import com.degloba.ecommerce.vendes.facturacio.domain.services.TaxAdvisorService;
import com.degloba.event.annotations.EventListener;


@EventListeners
public class BookKeepingListener {

	@Inject
	private BookKeeperService bookKeeper;
	
	@Inject
	private IVendaRepository vendaRepository;
		
	@Inject
	private TaxAdvisorService taxAdvisor;
	
	
	@Inject
	private PeticioFacturaFactory peticioFacturaFactory;
	
	@EventListener
	public void handle(OrdreEnviadaEvent event){
		// recuperem la compra a partir de l'Id de l'ordre
		Compra compra = vendaRepository.get(Compra.class, event.getComandaId());
		
		// recuperem el {@link Client} a partir de {@link Purchase}
		Client client = vendaRepository.get(Client.class, compra.getClientData().getAggregateId());
		
		PeticioFactura request  = peticioFacturaFactory.create(client, compra); 
		
		// Factura
		Factura factura = bookKeeper.issuance(request, taxAdvisor.suggestBestTax(client));
		
		vendaRepository.save(factura);
	}
}
