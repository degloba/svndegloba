package com.degloba.ecommerce.vendes.cqrs.commands;

import com.degloba.cqrs.command.annotations.ICommand;

import lombok.Value;

@ICommand
@Value
public class DetallsComandaCommand {
	
	public Long shippingAddressId;
	public Long deliveryOptionId;
	public Long paymentOptionId;
}
