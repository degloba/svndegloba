package com.degloba.ecommerce.vendes.compres.domain.persistence.rdbms.jpa;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.degloba.domain.annotations.AggregateRoot;

import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.jpa.ClientData;


/**
 * @category Compra
 * 
 * @author degloba
 *
 */
@Entity
@AggregateRoot
public class Compra extends BaseAggregateRoot{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//	@Fetch(FetchMode.JOIN)
	@OrderColumn(name = "itemNumber")
	//@JoinColumn(name = "purchase_id")
/*    @JoinColumns(
    	    {@JoinColumn(name = "purchase_id", referencedColumnName = "aggregateId",
    	                 insertable = false, updatable = false)
    	     })*/
	private List<CompraArticle> items;
	
	private boolean paid;

	@Embedded
	private ClientData clientData;

	@Temporal(TemporalType.DATE)
	private Date dataCompra;

	@Embedded
	private Money totalCost;

	
	private  Compra() {}

	public Compra(AggregateId aggregateId, ClientData clientData, List<CompraArticle> items, Date dataCompra,
			boolean paid, Money totalCost){
		this.aggregateId = aggregateId;
		this.clientData = clientData;
		this.items = items;
		this.dataCompra = dataCompra;
		this.paid = paid;
		this.totalCost = totalCost;
	}
	
	public Compra(AggregateId aggregateId) {
		// TODO Auto-generated constructor stub
		this.aggregateId = aggregateId;
	}

	public void confirm() {
		paid = true;
				
		//eventPublisher.publish(new ComandaEnviadaEvent(getAggregateId()));
	}
	
	public boolean estaPagada() {
		return paid;
	}
	
	public Money getTotalCost() {
		return totalCost;
	}
	
	public Date getDataCompra() {
		return dataCompra;
	}

	public ClientData getClientData() {
		return clientData;
	}
	
	public Collection<CompraArticle> getItems() {
		return (Collection<CompraArticle>) Collections.unmodifiableCollection(items);
	}


}
