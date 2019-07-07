package com.degloba.ecommerce.vendes.cqrs.commands;

import com.degloba.cqrs.command.annotations.Command;
import com.degloba.persistence.rdbms.jpa.AggregateId;


/**
 * @category 
 * 
 * @author degloba
 *
 */
@Command
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
		return comandaId;
	}
	
	public AggregateId getProducteId() {
		return producteId;
	}
	
	public int getQuantitat() {
		return quantitat;
	}
}
