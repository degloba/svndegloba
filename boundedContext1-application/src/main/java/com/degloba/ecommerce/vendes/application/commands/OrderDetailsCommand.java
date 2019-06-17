package com.degloba.ecommerce.vendes.application.commands;

import com.degloba.cqrs.command.annotations.Command;

@Command
public class OrderDetailsCommand {
	public Long shippingAddressId;
	public Long deliveryOptionId;
	public Long paymentOptionId;
}
