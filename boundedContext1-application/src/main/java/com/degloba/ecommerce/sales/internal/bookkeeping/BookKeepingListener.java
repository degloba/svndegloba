package com.degloba.ecommerce.sales.internal.bookkeeping;

import javax.inject.Inject;

import com.degloba.annotations.event.EventListener;
import com.degloba.annotations.event.EventListeners;
import com.degloba.ecommerce.canonicalmodel.events.OrderSubmittedEvent;
import com.degloba.ecommerce.sales.client.Client;
import com.degloba.ecommerce.sales.client.ClientRepository;
import com.degloba.ecommerce.sales.invoicing.BookKeeper;
import com.degloba.ecommerce.sales.invoicing.Invoice;
import com.degloba.ecommerce.sales.invoicing.IInvoiceRepository;
import com.degloba.ecommerce.sales.invoicing.InvoiceRequest;
import com.degloba.ecommerce.sales.invoicing.InvoiceRequestFactory;
import com.degloba.ecommerce.sales.invoicing.TaxAdvisor;
import com.degloba.ecommerce.sales.purchase.Purchase;
import com.degloba.ecommerce.sales.purchase.IPurchaseRepository;

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
	private ClientRepository clientRepository;
	
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
