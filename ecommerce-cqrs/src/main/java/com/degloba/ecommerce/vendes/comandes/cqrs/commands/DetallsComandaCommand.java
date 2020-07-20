package com.degloba.ecommerce.vendes.comandes.cqrs.commands;

import com.degloba.cqrs.commands.annotations.ICommandAnnotation;

import lombok.Value;

@ICommandAnnotation
@Value
public class DetallsComandaCommand {
	
	public Long shippingAddressId;
	public Long deliveryOptionId;
	public Long paymentOptionId;
}
