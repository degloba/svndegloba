package com.degloba.ecommerce.vendes.comandes.cqrs.commands;

import com.degloba.cqrs.command.annotations.ICommand;

import lombok.Value;

@ICommand
@Value
public class DetallsComandaCommand {
	
	public Long shippingAddressId;
	public Long deliveryOptionId;
	public Long paymentOptionId;
}
