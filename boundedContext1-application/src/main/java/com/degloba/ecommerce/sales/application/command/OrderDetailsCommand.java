package com.degloba.ecommerce.sales.application.command;

import com.degloba.cqrs.command.annotations.Command;

@Command
public class OrderDetailsCommand {
	public Long shippingAddressId;
	public Long deliveryOptionId;
	public Long paymentOptionId;
}
