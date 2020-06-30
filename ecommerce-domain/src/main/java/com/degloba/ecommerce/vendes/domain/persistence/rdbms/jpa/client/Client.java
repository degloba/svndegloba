package com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.client;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.degloba.domain.annotations.AggregateRoot;
import com.degloba.ecommerce.vendes.pagaments.domain.factories.PagamentsFactory;
import com.degloba.ecommerce.vendes.pagaments.domain.persistence.rdbms.jpa.Pagament;
import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.api.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.api.jpa.ClientData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author degloba
 * 
 * @category client
 *
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@AggregateRoot
public class Client extends BaseAggregateRoot{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String name;
	
	
	@Inject
	@Transient
	private PagamentsFactory pagamentsFactory;
	
	public ClientData generateSnapshot(){		
		return new ClientData(aggregateId, name);
	}

	public boolean canAfford(Money quantitat) {		
		return true;//TODO explore domain rules ex: credit limit
	}

	/**
	 * Sample model: one aggregate creates another.<br>
	 * Client model does not compose Payment - therefore it does not manage Payment lifecycle.<br>
	 * Application layer is responsible for managing Payment lifecycle;
	 * 
	 * @param quantitat
	 * @return
	 */
	public Pagament charge(Money quantitat) {
		if (! canAfford(quantitat)){			
			domainError("Can not afford: " + quantitat);
		}
		// TODO facade to the payment module
		
		return pagamentsFactory.creaPagament(generateSnapshot(), quantitat);
	}

}
