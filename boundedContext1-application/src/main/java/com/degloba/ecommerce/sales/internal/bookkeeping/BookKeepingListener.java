package com.degloba.ecommerce.sales.internal.bookkeeping;

import javax.inject.Inject;

import com.degloba.ecommerce.canonicalmodel.events.OrderSubmittedEvent;

// Ecommerce 
import com.degloba.ecommerce.sales.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.sales.client.domain.persistence.rdbms.jpa.IClientRepository;
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.BookKeeper;
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.Invoice;
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.IInvoiceRepository;
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.InvoiceRequest;
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.InvoiceRequestFactory;
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.TaxAdvisor;
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
	private IPurchaseRepository purchaseRepository;
	
	@Inject
	private IInvoiceRepository invoiceRepository;
	
	@Inject
	private TaxAdvisor taxAdvisor;
	
	@Inject
	private IClientRepository clientRepository;
	
	@Inject
	private InvoiceRequestFactory invoiceRequestFactory;
	
	@EventListener
	public void handle(OrderSubmittedEvent event){
		Purchase purchase = purchaseRepository.load(event.getOrderId());
		
		Client client = clientRepository.load(purchase.getClientData().getAggregateId());
		InvoiceRequest request  = invoiceRequestFactory.create(client, purchase); 
		Invoice invoice = bookKeeper.issuance(request, taxAdvisor.suggestBestTax(client));
		
		invoiceRepository.save(invoice);
	}
}
