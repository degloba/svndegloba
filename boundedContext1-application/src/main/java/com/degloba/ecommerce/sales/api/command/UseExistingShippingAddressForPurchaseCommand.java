package com.degloba.ecommerce.sales.api.command;

import command.annotations.Command;

@Command
public class UseExistingShippingAddressForPurchaseCommand {

	private Long orderId;
	private Long shippingAddressId;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getShippingAddressId() {
		return shippingAddressId;
	}

	public void setShippingAddressId(Long shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}
}
