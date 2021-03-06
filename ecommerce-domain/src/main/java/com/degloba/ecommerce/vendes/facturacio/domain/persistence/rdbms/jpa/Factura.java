package com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa;

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
import javax.persistence.OneToMany;

import com.degloba.domain.annotations.AggregateRoot;

import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.api.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.api.jpa.ClientData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/**
 * @category entitat de persistenica que defineix una factura
 * 
 * @author degloba
 * 
 */
@AggregateRoot
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Factura extends BaseAggregateRoot {

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

}
