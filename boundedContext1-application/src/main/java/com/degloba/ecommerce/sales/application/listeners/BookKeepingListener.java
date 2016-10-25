package com.degloba.ecommerce.sales.application.listeners;

import javax.inject.Inject;


// Ecommerce 
import com.degloba.ecommerce.sales.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.sales.domain.events.OrderSubmittedEvent;
import com.degloba.ecommerce.sales.domain.persistence.rdbms.jpa.ISalesRepository;



import com.degloba.ecommerce.sales.invoicing.domain.factories.InvoiceRequestFactory;
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.Invoice;
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.InvoiceRequest;
import com.degloba.ecommerce.sales.invoicing.domain.services.BookKeeper;
import com.degloba.ecommerce.sales.invoicing.domain.services.TaxAdvisor;
import com.degloba.ecommerce.sales.purchase.domain.persistence.rdbms.jpa.Purchase;
// Events
import com.degloba.event.annotations.EventListeners;
import com.degloba.event.annotations.EventListener;


@EventListeners
public class BookKeepingListener {

	@Inject
	private BookKeeper bookKeeper;
	
	@Inject
	private ISalesRepository salesRepository;
		
	@Inject
	private TaxAdvisor taxAdvisor;
	
	
	@Inject
	private InvoiceRequestFactory invoiceRequestFactory;
	
	@EventListener
	public void handle(OrderSubmittedEvent event){
		Purchase purchase = salesRepository.get(Purchase.class,event.getOrderId());
		
		Client client = salesRepository.get(Client.class,purchase.getClientData().getAggregateId());
		InvoiceRequest request  = invoiceRequestFactory.create(client, purchase); 
		Invoice invoice = bookKeeper.issuance(request, taxAdvisor.suggestBestTax(client));
		
		salesRepository.save(invoice);
	}
}
