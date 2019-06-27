package com.degloba.ecommerce.crm.application.commands;

import java.io.Serializable;

import com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa.Client.EstatClient;
import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.cqrs.command.annotations.Command;


/**
 * @author degloba
 *
 */
@SuppressWarnings("serial")
@Command
public class CanviarEstatClientCommand implements Serializable{

	private AggregateId clientId;
	
	private EstatClient estatClient;

	public CanviarEstatClientCommand(AggregateId clientId, EstatClient estatClient) {
		super();
		this.clientId = clientId;
		this.estatClient = estatClient;
	}

	public AggregateId getClientId() {
		return clientId;
	}

	public EstatClient getEstatClient() {
		return estatClient;
	}
	
	
}
