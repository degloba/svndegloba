package com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.degloba.domain.annotations.ValueObject;
import com.degloba.persistence.rdbms.api.jpa.ClientData;


/**
 * @category
 * 
 * @author degloba
 *
 */
@ValueObject
public class PeticioFactura {

	private ClientData client;	
	private List<RequestItem> items = new ArrayList<RequestItem>();
	
	public PeticioFactura(ClientData client){
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
