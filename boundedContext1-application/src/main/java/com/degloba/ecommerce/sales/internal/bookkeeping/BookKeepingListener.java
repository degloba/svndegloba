package com.degloba.ecommerce.sales.internal.bookkeeping;

import javax.inject.Inject;

import com.degloba.annotations.event.EventListener;
import com.degloba.annotations.event.EventListeners;
import com.degloba.ecommerce.canonicalmodel.events.OrderSubmittedEvent;

// Domain
import com.degloba.ecommerce.sales.client.domain.Client;
import com.degloba.ecommerce.sales.client.domain.IClientRepository;
import com.degloba.ecommerce.sales.invoicing.domain.BookKeeper;
import com.degloba.ecommerce.sales.invoicing.domain.Invoice;
import com.degloba.ecommerce.sales.invoicing.domain.IInvoiceRepository;
import com.degloba.ecommerce.sales.invoicing.domain.InvoiceRequest;
import com.degloba.ecommerce.sales.invoicing.domain.InvoiceRequestFactory;
import com.degloba.ecommerce.sales.invoicing.domain.TaxAdvisor;
import com.degloba.ecommerce.sales.purchase.domain.Purchase;
import com.degloba.ecommerce.sales.purchase.domain.IPurchaseRepository;

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
