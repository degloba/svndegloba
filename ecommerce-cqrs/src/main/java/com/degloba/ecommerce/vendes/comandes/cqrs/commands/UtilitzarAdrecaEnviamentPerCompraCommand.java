package com.degloba.ecommerce.vendes.comandes.cqrs.commands;

import com.degloba.cqrs.commands.annotations.ICommandAnnotation;

import lombok.Getter;
import lombok.Setter;

@ICommandAnnotation
public class UtilitzarAdrecaEnviamentPerCompraCommand {

	@Getter @Setter private Long comandaId;
	@Getter @Setter private Long adrecaEnviamentId;

}
