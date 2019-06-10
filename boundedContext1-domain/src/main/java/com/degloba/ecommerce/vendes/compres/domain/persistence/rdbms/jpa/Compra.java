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
import com.degloba.ecommerce.vendes.domain.events.OrdreEnviadaEvent;
import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.domain.ClientData;
import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;


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
	private List<CompraItem> items;
	
	private boolean paid;

	@Embedded
	private ClientData clientData;

	@Temporal(TemporalType.DATE)
	private Date purchaseDate;

	@Embedded
	private Money totalCost;

	
	private  Compra() {}

	public Compra(AggregateId aggregateId, ClientData clientData, List<CompraItem> items, Date purchaseDate,
			boolean paid, Money totalCost){
		this.aggregateId = aggregateId;
		this.clientData = clientData;
		this.items = items;
		this.purchaseDate = purchaseDate;
		this.paid = paid;
		this.totalCost = totalCost;
	}
	
	public void confirm() {
		paid = true;
		
		eventPublisher.publish(new OrdreEnviadaEvent(getAggregateId()));
	}
	
	public boolean isPaid() {
		return paid;
	}
	
	public Money getTotalCost() {
		return totalCost;
	}
	
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public ClientData getClientData() {
		return clientData;
	}
	
	public Collection<CompraItem> getItems() {
		return (Collection<CompraItem>) Collections.unmodifiableCollection(items);
	}


}
