package com.degloba.ecommerce.vendes.ofertes.domain.factories;

import com.degloba.domain.annotations.DomainFactory;
import com.degloba.ecommerce.vendes.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.vendes.ofertes.descomptes.QuantityDiscount;
import com.degloba.ecommerce.vendes.ofertes.domain.policies.DescomptePolicy;

/*
 * Fàbrica de Descompte
 */
@DomainFactory
public class DescompteFactory {

	public DescomptePolicy create(Client client) {
		// TODO explore domain rules
		return new QuantityDiscount(20, 3);//20% for over 3 items
	}

}
