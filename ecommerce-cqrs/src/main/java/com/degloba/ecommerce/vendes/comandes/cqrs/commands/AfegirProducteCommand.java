package com.degloba.ecommerce.vendes.comandes.cqrs.commands;

import com.degloba.cqrs.command.annotations.ICommand;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;

import lombok.Value;


/**
 * @category command
 * 
 * @author degloba
 *
 */
@ICommand
@Value
public class AfegirProducteCommand {

	private AggregateId comandaId;
	private AggregateId producteId;
	private int quantitat;
	
	public AfegirProducteCommand(AggregateId comandaId, AggregateId producteId, int quantitat) {
		this.comandaId = comandaId;
		this.producteId = producteId;
		this.quantitat = quantitat;
	}

	public AggregateId getComandaId() {
		// TODO Auto-generated method stub
		return null;
	}

	public AggregateId getProducteId() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
