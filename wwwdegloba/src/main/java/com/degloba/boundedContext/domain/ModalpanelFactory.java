package com.degloba.boundedContext.domain;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import domain.annotations.DomainFactory;
import domain.canonicalmodel.publishedlanguage.AggregateId;
import domain.sharedkernel.exceptions.DomainOperationException;
import domain.support.DomainEventPublisher;

@DomainFactory
public class ModalpanelFactory {

	@Inject
	private AutowireCapableBeanFactory spring;
	
	/*@Inject
	private DomainEventPublisher domainEventPublisher;*/

	/**
	 * 
	 * @param orderId correlation id - correlates purchases and reservations  
	 * @param client
	 * @param offer
	 * @return
	 */
/*	public Purchase create(AggregateId orderId, Client client, Offer offer){
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
	}*/
	


	/*public Modalpanel createModalpanel(ClientData clientData, Money amount){
		//TODO validate

		AggregateId aggregateId = AggregateId.generate();
		domainEventPublisher.publish(new ClientPaidEvent(aggregateId, clientData, amount));
		return new Modalpanel(aggregateId, clientData, amount);
	}*/

}
