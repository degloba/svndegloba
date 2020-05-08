package com.degloba.ecommerce.compres.domain.factories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.degloba.domain.annotations.DomainFactory;
import com.degloba.ecommerce.compres.domain.persistence.rdbms.jpa.Compra;
import com.degloba.ecommerce.compres.domain.persistence.rdbms.jpa.CompraArticle;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.client.Client;
import com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa.Factura;
import com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa.Oferta;
import com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa.OfertaItem;
import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;
import com.degloba.persistence.rdbms.api.jpa.ClientData;
import com.degloba.persistence.rdbms.api.jpa.exceptions.DomainOperationException;


/**
 * @category Fabrica de {@link Compra} d'un {@link ClientData}
 * 
 * @author degloba
 *
 */
@DomainFactory
public class CompresFactory {

	@Inject
	private AutowireCapableBeanFactory spring;
	
	/**
	 * 
	 * @param comandaId correlation id - correlates {@link Compra}s and reservations  
	 * @param client
	 * @param oferta
	 * @return
	 */
	public Compra create(AggregateId comandaId, Client client, Oferta oferta){
		if (! potComprar(client, oferta.obtenirArticlesDisponibles()))
			throw new DomainOperationException(client.getAggregateId(), "client can not purchase");
		
		ArrayList<CompraArticle> items = new ArrayList<CompraArticle>(oferta.obtenirArticlesDisponibles().size());
		Money costTotalCompra = Money.ZERO;
		
		for (OfertaItem item : oferta.obtenirArticlesDisponibles()) {
			CompraArticle compraArticle = new CompraArticle(item.getProductData(), item.getQuantitat(), item.getTotalCost());
			items.add(compraArticle);
			costTotalCompra = costTotalCompra.add(compraArticle.getTotalCost());
		}
		
		Compra compra = new Compra(comandaId, client.generateSnapshot(),
				items, new Date(), false, costTotalCompra);
		
		spring.autowireBean(compra);
		
		return compra;
	}

	private boolean potComprar(Client client, List<OfertaItem> articlesDisponibles) {
		return true;//TODO explore domain rules
	}
}
