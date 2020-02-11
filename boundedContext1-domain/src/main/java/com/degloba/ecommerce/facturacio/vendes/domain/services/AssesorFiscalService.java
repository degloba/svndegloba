package com.degloba.ecommerce.facturacio.vendes.domain.services;

import com.degloba.domain.annotations.DomainService;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.client.Client;
import com.degloba.ecommerce.vendes.facturacio.domain.policies.IImpostPolicy;
import com.degloba.ecommerce.vendes.facturacio.domain.policies.ImpostPerDefectePolicy;

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
