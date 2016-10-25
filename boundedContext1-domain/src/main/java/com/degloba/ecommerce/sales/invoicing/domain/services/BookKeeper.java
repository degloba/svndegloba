package com.degloba.ecommerce.sales.invoicing.domain.services;

import javax.inject.Inject;

import com.degloba.domain.annotations.DomainService;

import com.degloba.domain.sharedkernel.Money;

import com.degloba.ecommerce.sales.invoicing.domain.factories.InvoiceFactory;
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.Invoice;
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.InvoiceLine;
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.InvoiceRequest;
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.RequestItem;
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.Tax;
import com.degloba.ecommerce.sales.invoicing.domain.policies.ITaxPolicy;


/**
 * Sample Domain Service that contains logic that:
 * <ul>
 * <li> Does not have a natural place in any aggregate - we don't want to bloat Order with issuance of the Invoice
 * <li> Has broad dependencies - we don't want Order to become a 'God Class'
 * <li> Is used only (or not many) in one Use Case/user Story so is not essential for any Aggregate
 * <ul>
 * 
 * Notice that this Domain Service is managed by Container in order to be able to inject dependencies like Repo  
 * 
 * @author degloba
 *
 */
@DomainService
public class BookKeeper {
	

	@Inject
	private InvoiceFactory invoiceFactory;
	
	public Invoice issuance(InvoiceRequest invoiceRequest, ITaxPolicy taxPolicy){
		Invoice invoice = invoiceFactory.create(invoiceRequest.getClientData());
		
		for (RequestItem item : invoiceRequest.getItems()){
			Money net = item.getTotalCost();			
			Tax tax = taxPolicy.calculateTax(item.getProductData().getType(), net);			
						
			InvoiceLine invoiceLine = new InvoiceLine(item.getProductData(), item.getQuantity(), net, tax);			
			invoice.addItem(invoiceLine);
		}
		
		return invoice;
	}
	
}
