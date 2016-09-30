package com.degloba.ecommerce.sales.purchase.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;


import com.degloba.domain.annotations.AggregateRoot;


import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.ClientData;
import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.domain.sharedkernel.Money;
import com.google.appengine.api.datastore.Key;

/**
 * Models fact of purchase.
 * 
 * @author degloba
 *
 */
@Entity
@AggregateRoot
public class Purchase extends BaseAggregateRoot{

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	///////@Fetch(FetchMode.JOIN)
	@OrderColumn(name = "itemNumber")
	@JoinColumn(name = "purchase_id")
	private List<PurchaseItem> items;
	
	private boolean paid;

	@Embedded
	private ClientData clientData;

	private Date purchaseDate;

	@Embedded
	private Money totalCost;

	
	@SuppressWarnings("unused")
	private  Purchase() {}

	Purchase(Key aggregateId, ClientData clientData, List<PurchaseItem> items, Date purchaseDate,
			boolean paid, Money totalCost){
		///////this.aggregateId = aggregateId;
		this.clientData = clientData;
		this.items = items;
		this.purchaseDate = purchaseDate;
		this.paid = paid;
		this.totalCost = totalCost;
	}
	
	public void confirm() {
		paid = true;
		////////////////////eventPublisher.publish(new OrderSubmittedEvent(getAggregateId()));
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
	
	public Collection<PurchaseItem> getItems() {
		return (Collection<PurchaseItem>) Collections.unmodifiableCollection(items);
	}
	
}
