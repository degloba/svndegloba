package com.degloba.boundedContext.application.api.command;

import command.annotations.Command;
import domain.canonicalmodel.publishedlanguage.AggregateId;

@Command()
public class AddModalpanelCommand {

	private AggregateId orderId;
	private AggregateId modalpanelId;
	private int quantity;

	public AddModalpanelCommand(AggregateId orderId, AggregateId modalpanelId,
		int quantity) {
		this.orderId = orderId;
		this.modalpanelId = modalpanelId;
		this.quantity = quantity;
	}

	public AggregateId getOrderId() {
		return orderId;
	}

	public AggregateId getModalpanelId() {
		return modalpanelId;
	}

	public int getQuantity() {
		return quantity;
	}
}
