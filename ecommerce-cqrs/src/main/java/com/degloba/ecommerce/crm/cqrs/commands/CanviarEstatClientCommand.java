package com.degloba.ecommerce.crm.cqrs.commands;

import java.io.Serializable;

import lombok.Value;

import com.degloba.cqrs.commands.annotations.ICommandAnnotation;
import com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa.Client.EstatClient;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;


/**
 * @category Command que indica que cal canviar l'estat d'un client
 * 
 * @author degloba
 *
 */
@SuppressWarnings("serial")
@ICommandAnnotation
@Value
public class CanviarEstatClientCommand implements Serializable {

	private AggregateId clientId;	
	private EstatClient estatClient;

	public CanviarEstatClientCommand(AggregateId clientId, EstatClient estatClient) {
		super();
		this.clientId = clientId;
		this.estatClient = estatClient;
	}
}
