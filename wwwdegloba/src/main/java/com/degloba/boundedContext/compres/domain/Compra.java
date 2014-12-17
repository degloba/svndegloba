package com.degloba.boundedContext.compres.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

/*import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;*/



//import com.degloba.canonicalmodel.events.OrderSubmittedEvent;


import com.degloba.sharedkernel.Money;

import domain.annotations.AggregateRoot;
import domain.canonicalmodel.publishedlanguage.AggregateId;
import domain.canonicalmodel.publishedlanguage.ClientData;
import domain.support.BaseAggregateRoot;
import domain.support.DomainEventPublisher;



/**
 * Models fact of purchase.
 * 
 * @author Slawek
 *
 */
@Entity
@AggregateRoot
public class Compra extends BaseAggregateRoot{

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	//@Fetch(FetchMode.JOIN)
	@OrderColumn(name = "itemNumber")
	@JoinColumn(name = "purchase_id")
	private List<CompraItem> items;
	
	private boolean paid;

	@Embedded
	private ClientData clientData;

	private Date purchaseDate;

	@Embedded
	private Money totalCost;

	
	@SuppressWarnings("unused")
	public  Compra() {}
	
/*	
	@Inject
	private DomainEventPublisher domainEventPublisher;*/

	Compra(AggregateId aggregateId, ClientData clientData, List<CompraItem> items, Date purchaseDate,
			boolean paid, Money totalCost){
		//this.aggregateId = aggregateId;
		this.clientData = clientData;
		this.items = items;
		this.purchaseDate = purchaseDate;
		this.paid = paid;
		this.totalCost = totalCost;
	}
	
	public void confirm() {
		paid = true;
		////eventPublisher.publish(new OrderSubmittedEvent(getAggregateId()));
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

