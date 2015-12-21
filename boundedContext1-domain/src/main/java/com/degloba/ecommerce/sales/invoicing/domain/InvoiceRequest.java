package com.degloba.ecommerce.sales.invoicing.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.degloba.domain.annotations.ValueObject;
import com.degloba.domain.canonicalmodel.publishedlanguage.ClientData;

@ValueObject
public class InvoiceRequest {

	private ClientData client;	
	private List<RequestItem> items = new ArrayList<RequestItem>();
	
	public InvoiceRequest(ClientData client){
		this.client = client;
	}
	
	public void add(RequestItem item){
		items.add(item);
	}
	
	public ClientData getClient() {
		return client;
	}
	
	public Collection<RequestItem> getItems() {
		return Collections.unmodifiableCollection(items);
	}

	public ClientData getClientData() {
		return client;
	}
}
