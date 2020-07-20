package com.degloba.ecommerce.vendes.comandes.cqrs.commands;


import com.degloba.cqrs.commands.annotations.ICommandAnnotation;

import lombok.Getter;
import lombok.Setter;

@ICommandAnnotation
public class SetNewShippingAddressForOrderCommand {
	
	@Getter @Setter private Long comandaId;
	@Getter @Setter private String fullName;
	@Getter @Setter private String streetAddress;
	@Getter @Setter private String city;
	@Getter @Setter private String region;
	@Getter @Setter private String postalCode;
	@Getter @Setter private String country;
	@Getter @Setter private String phoneNumber;

}
