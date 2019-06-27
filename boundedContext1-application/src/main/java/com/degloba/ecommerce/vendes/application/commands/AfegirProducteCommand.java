package com.degloba.ecommerce.vendes.application.commands;

import com.degloba.cqrs.command.annotations.Command;
import com.degloba.persistence.domain.AggregateId;


@Command
public class AddProdctCommand {

	private AggregateId orderId;
	private AggregateId productId;
	private int quantity;
	
	public AddProdctCommand(AggregateId orderId, AggregateId productId,
			int quantity) {
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	public AggregateId getOrderId() {
		return orderId;
	}
	
	public AggregateId getProductId() {
		return productId;
	}
	
	public int getQuantity() {
		return quantity;
	}
}
