package com.degloba.ecommerce.vendes.compres.domain.factories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.degloba.domain.annotations.DomainFactory;
import com.degloba.ecommerce.vendes.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.vendes.compres.domain.persistence.rdbms.jpa.Compra;
import com.degloba.ecommerce.vendes.compres.domain.persistence.rdbms.jpa.CompraItem;
import com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa.Oferta;
import com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa.OfertaItem;

import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.persistence.rdbms.jpa.exceptions.DomainOperationException;



@DomainFactory
public class CompresFactory {

	@Inject
	private AutowireCapableBeanFactory spring;
	
	/**
	 * 
	 * @param comandaId correlation id - correlates purchases and reservations  
	 * @param client
	 * @param oferta
	 * @return
	 */
	public Compra create(AggregateId comandaId, Client client, Oferta oferta){
		if (! canPurchse(client, oferta.getAvailableItems()))
			throw new DomainOperationException(client.getAggregateId(), "client can not purchase");
		
		ArrayList<CompraItem> items = new ArrayList<CompraItem>(oferta.getAvailableItems().size());
		Money purchaseTotlCost = Money.ZERO;
		
		for (OfertaItem item : oferta.getAvailableItems()) {
			CompraItem compraItem = new CompraItem(item.getProductData(), item.getQuantitat(), item.getTotalCost());
			items.add(compraItem);
			purchaseTotlCost = purchaseTotlCost.add(compraItem.getTotalCost());
		}
		
		Compra compra = new Compra(comandaId, client.generateSnapshot(),
				items, new Date(), false, purchaseTotlCost);
		
		spring.autowireBean(compra);
		
		return compra;
	}

	private boolean canPurchse(Client client, List<OfertaItem> availabeItems) {
		return true;//TODO explore domain rules
	}
}
