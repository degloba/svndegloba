package com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;

import com.degloba.domain.annotations.AggregateRoot;

import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.api.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.api.jpa.ClientData;



/**
 * @category entitat de persistenica que defineix una factura
 * 
 * @author degloba
 * 
 */
@AggregateRoot
@Entity
public class Factura extends BaseAggregateRoot {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Embedded
	private ClientData client;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "denomination", column = @Column(name = "NET_DENOMINATION")),
			@AttributeOverride(name = "currencyCode", column = @Column(name = "NET_CURRENCYCODE")) })
	private Money net;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "denomination", column = @Column(name = "gros_denomination")),
		@AttributeOverride(name = "currencyCode", column = @Column(name = "gros_currencyCode")) })
	private Money gros;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	//@JoinColumn(name = "invoiceId")
	///////////@Fetch(FetchMode.JOIN)
/*    @JoinColumns(
    	    {@JoinColumn(name = "invoiceId", referencedColumnName = "aggregateId",
    	                 insertable = false, updatable = false)
    	     })*/
	private List<LiniaFacturacio> items;

	public Factura(long invoiceId, ClientData client) {
		///////this.aggregateId = invoiceId;
		this.client = client;
		this.items = new ArrayList<LiniaFacturacio>();
		
		this.net = Money.ZERO;
		this.gros = Money.ZERO;
	}
	
	/**
	 * For JPA Only
	 */
	private Factura(){}

	public void addItem(LiniaFacturacio item) {
		items.add(item);

		net = net.add(item.getNet());
		gros = gros.add(item.getGros());
	}

	/**
	 * 
	 * @return immutable projection
	 */
	public List<LiniaFacturacio> getItems() {
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
