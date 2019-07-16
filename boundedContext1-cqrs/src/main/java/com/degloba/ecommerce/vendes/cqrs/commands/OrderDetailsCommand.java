package com.degloba.ecommerce.vendes.cqrs.commands;

import com.degloba.cqrs.command.annotations.ICommand;

@ICommand
public class OrderDetailsCommand {
	public Long shippingAddressId;
	public Long deliveryOptionId;
	public Long paymentOptionId;
}
