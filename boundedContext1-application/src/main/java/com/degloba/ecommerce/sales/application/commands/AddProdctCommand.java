package com.degloba.ecommerce.sales.application.commands;

import com.degloba.cqrs.command.annotations.Command;


@Command
public class AddProdctCommand {

	private long orderId;
	private long productId;
	private int quantity;
	
	public AddProdctCommand(long orderId, long productId,
			int quantity) {
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	public long getOrderId() {
		return orderId;
	}
	
	public long getProductId() {
		return productId;
	}
	
	public int getQuantity() {
		return quantity;
	}
}
