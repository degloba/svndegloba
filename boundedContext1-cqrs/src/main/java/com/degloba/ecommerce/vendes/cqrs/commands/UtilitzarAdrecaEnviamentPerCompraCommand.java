package com.degloba.ecommerce.vendes.cqrs.commands;

import com.degloba.cqrs.command.annotations.ICommand;

import lombok.Getter;
import lombok.Setter;

@ICommand
public class UtilitzarAdrecaEnviamentPerCompraCommand {

	@Getter @Setter private Long comandaId;
	@Getter @Setter private Long adrecaEnviamentId;

}
