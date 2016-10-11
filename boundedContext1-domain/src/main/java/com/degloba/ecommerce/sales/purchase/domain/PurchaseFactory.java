package com.degloba.ecommerce.sales.purchase.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.degloba.domain.annotations.DomainFactory;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.ecommerce.sales.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.sales.offer.domain.Offer;
import com.degloba.ecommerce.sales.offer.domain.OfferItem;
import com.degloba.domain.sharedkernel.Money;


import com.degloba.domain.sharedkernel.exceptions.DomainOperationException;

@DomainFactory
public class PurchaseFactory {

	@Inject
	private AutowireCapableBeanFactory spring;
	
	/**
	 * 
	 * @param orderId correlation id - correlates purchases and reservations  
	 * @param client
	 * @param offer
	 * @return
	 */
	public Purchase create(long orderId, Client client, Offer offer){
		if (! canPurchse(client, offer.getAvailabeItems()))
			throw new DomainOperationException(client.getAggregateId(), "client can not purchase");
		
		ArrayList<PurchaseItem> items = new ArrayList<PurchaseItem>(offer.getAvailabeItems().size());
		Money purchaseTotlCost = Money.ZERO;
		
		for (OfferItem item : offer.getAvailabeItems()) {
			PurchaseItem purchaseItem = new PurchaseItem(item.getProductData(), item.getQuantity(), item.getTotalCost());
			items.add(purchaseItem);
			purchaseTotlCost = purchaseTotlCost.add(purchaseItem.getTotalCost());
		}
		
		Purchase purchase = new Purchase(orderId, client.generateSnapshot(),
				items, new Date(), false, purchaseTotlCost);
		
		spring.autowireBean(purchase);
		
		return purchase;
	}

	private boolean canPurchse(Client client, List<OfferItem> availabeItems) {
		return true;//TODO explore domain rules
	}
}
