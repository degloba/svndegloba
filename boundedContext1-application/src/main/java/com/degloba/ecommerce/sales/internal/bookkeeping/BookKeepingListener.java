package com.degloba.ecommerce.sales.internal.bookkeeping;

import javax.inject.Inject;

import com.degloba.ecommerce.canonicalmodel.events.OrderSubmittedEvent;

// Ecommerce 
import com.degloba.ecommerce.sales.client.domain.persistence.rdbms.jpa.Client;

import com.degloba.ecommerce.sales.domain.persistence.rdbms.jpa.ISalesRepository;
import com.degloba.ecommerce.sales.invoicing.domain.BookKeeper;
import com.degloba.ecommerce.sales.invoicing.domain.Invoice;
import com.degloba.ecommerce.sales.invoicing.domain.InvoiceRequest;
import com.degloba.ecommerce.sales.invoicing.domain.InvoiceRequestFactory;
import com.degloba.ecommerce.sales.invoicing.domain.TaxAdvisor;
import com.degloba.ecommerce.sales.purchase.domain.Purchase;
import com.degloba.ecommerce.sales.purchase.domain.IPurchaseRepository;

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
