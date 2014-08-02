package com.degloba.boundedContext.application.api.command;

import command.annotations.Command;

@Command()
public class AddModalpanelCommand<K> {

	private K orderId;
	private K modalpanelId;
	private int quantity;

	public AddModalpanelCommand(K orderId, K modalpanelId,int quantity) {
		this.orderId = orderId;
		this.modalpanelId = modalpanelId;
		this.quantity = quantity;
	}

	public K getOrderId() {
		return orderId;
	}

	public K getModalpanelId() {
		return modalpanelId;
	}

	public int getQuantity() {
		return quantity;
	}
}
