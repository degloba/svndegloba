package com.degloba.boundedContext.compres.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;





import com.degloba.boundedContext.clients.domain.Client;
import com.degloba.sharedkernel.Money;

import domain.annotations.DomainFactory;
import domain.canonicalmodel.publishedlanguage.AggregateId;
import domain.sharedkernel.exceptions.DomainOperationException;
import domain.support.DomainEventPublisher;

@DomainFactory
public class CompraFactory {

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
	public Compra create(AggregateId orderId, Client client, Oferta oferta){
		if (! canPurchse(client, oferta.getAvailabeItems()))
			//throw new DomainOperationException(client.getAggregateId(), "client can not purchase");
throw null;
			
		ArrayList<CompraItem> items = new ArrayList<CompraItem>(oferta.getAvailabeItems().size());
		Money purchaseTotlCost = Money.ZERO;

		for (OfertaItem item : oferta.getAvailabeItems()) {
			CompraItem purchaseItem = new CompraItem(item.getProductData(), item.getQuantity(), item.getTotalCost());
			items.add(purchaseItem);
			purchaseTotlCost = purchaseTotlCost.add(purchaseItem.getTotalCost());
		}

		Compra compra = new Compra(orderId, client.generateSnapshot(),
				items, new Date(), false, purchaseTotlCost);

		spring.autowireBean(compra);

		return compra;
	}

	private boolean canPurchse(Client client, List<OfertaItem> availabeItems) {
		return true;//TODO explore domain rules
	}


}
