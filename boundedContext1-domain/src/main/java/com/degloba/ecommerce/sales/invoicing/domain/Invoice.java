package com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.ClientData;
import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.domain.sharedkernel.Money;
import com.google.appengine.api.datastore.Key;

/**
 * 
 * @author degloba
 * 
 */
//@AggregateRoot
@Entity
public class Invoice extends BaseAggregateRoot {

	@Embedded
	private ClientData client;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "denomination", column = @Column(name = "net_denomination")),
			@AttributeOverride(name = "currencyCode", column = @Column(name = "net_currencyCode")) })
	private Money net;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "denomination", column = @Column(name = "gros_denomination")),
		@AttributeOverride(name = "currencyCode", column = @Column(name = "gros_currencyCode")) })
	private Money gros;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "invoiceId")
	///////////@Fetch(FetchMode.JOIN)
	private List<InvoiceLine> items;

	Invoice(Key invoiceId, ClientData client) {
		///////this.aggregateId = invoiceId;
		this.client = client;
		this.items = new ArrayList<InvoiceLine>();
		
		this.net = Money.ZERO;
		this.gros = Money.ZERO;
	}
	
	/**
	 * For JPA Only
	 */
	@SuppressWarnings("unused")
	private Invoice(){}

	public void addItem(InvoiceLine item) {
		items.add(item);

		net = net.add(item.getNet());
		gros = gros.add(item.getGros());
	}

	/**
	 * 
	 * @return immutable projection
	 */
	public List<InvoiceLine> getItems() {
		return Collections.unmodifiableList(items);
	}

	public ClientData getClient() {
		return client;
	}

	public Money getNet() {
		return net;
	}

	public Money getGros() {
		return gros;
	}

}
