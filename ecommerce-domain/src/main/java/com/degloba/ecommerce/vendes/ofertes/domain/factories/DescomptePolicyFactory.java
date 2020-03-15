package com.degloba.ecommerce.vendes.ofertes.domain.factories;

import com.degloba.domain.annotations.DomainFactory;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.client.Client;
import com.degloba.ecommerce.vendes.ofertes.descomptes.domain.policies.IDescomptePolicy;
import com.degloba.ecommerce.vendes.ofertes.descomptes.domain.policies.QuantitatDescomptePolicy;

/**
 * @category Fàbrica de {@link IDescomptePolicy}
 */
@DomainFactory
public class DescomptePolicyFactory {

	public IDescomptePolicy crea(Client client) {
		// TODO explore domain rules
		return new QuantitatDescomptePolicy(20, 3);//20% for over 3 items
	}

}
