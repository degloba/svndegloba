package com.degloba.ecommerce.sales.api.command;

import command.annotations.Command;

@Command
public class OrderDetailsCommand {
	public Long shippingAddressId;
	public Long deliveryOptionId;
	public Long paymentOptionId;
}
