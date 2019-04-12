package com.degloba.ecommerce.sales.purchase.domain.factories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.degloba.domain.annotations.DomainFactory;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.ecommerce.sales.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.sales.offer.domain.persistence.rdbms.jpa.Offer;
import com.degloba.ecommerce.sales.offer.domain.persistence.rdbms.jpa.OfferItem;
import com.degloba.ecommerce.sales.purchase.domain.persistence.rdbms.jpa.Purchase;
import com.degloba.ecommerce.sales.purchase.domain.persistence.rdbms.jpa.PurchaseItem;
import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.domain.sharedkernel.exceptions.DomainOperationException;


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
	public Purchase create(AggregateId orderId, Client client, Offer offer){
		if (! canPurchse(client, offer.getAvailableItems()))
			throw new DomainOperationException(client.getAggregateId(), "client can not purchase");
		
		ArrayList<PurchaseItem> items = new ArrayList<PurchaseItem>(offer.getAvailableItems().size());
		Money purchaseTotlCost = Money.ZERO;
		
		for (OfferItem item : offer.getAvailableItems()) {
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
