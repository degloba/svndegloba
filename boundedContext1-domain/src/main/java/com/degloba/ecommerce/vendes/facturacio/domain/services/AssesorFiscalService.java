package com.degloba.ecommerce.vendes.facturacio.domain.services;

import com.degloba.domain.annotations.DomainService;
import com.degloba.ecommerce.vendes.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.vendes.facturacio.domain.policies.ImpostPerDefectePolicy;
import com.degloba.ecommerce.vendes.facturacio.domain.policies.IImpostPolicy;

/**
 * @author degloba
 * 
 * @category Servei Assessor fiscal
 */
@DomainService
public class AssesorFiscalService {

	public IImpostPolicy suggereixMillorImpost(Client client){
		//TODO explore domain rules
		return new ImpostPerDefectePolicy();
	}
}
