package com.degloba.ecommerce.vendes.ofertes.domain.factories;

import com.degloba.domain.annotations.DomainFactory;
import com.degloba.ecommerce.vendes.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.vendes.ofertes.descomptes.QuantitatDescompte;
import com.degloba.ecommerce.vendes.ofertes.domain.policies.DescomptePolicy;

/*
 * Fàbrica de Descompte
 */
@DomainFactory
public class DescompteFactory {

	public DescomptePolicy crea(Client client) {
		// TODO explore domain rules
		return new QuantitatDescompte(20, 3);//20% for over 3 items
	}

}
