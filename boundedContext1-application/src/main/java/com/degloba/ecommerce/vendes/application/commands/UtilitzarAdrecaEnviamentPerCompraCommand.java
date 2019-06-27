package com.degloba.ecommerce.vendes.application.commands;

import com.degloba.cqrs.command.annotations.Command;

@Command
public class UtilitzarAdrecaEnviamentPerCompraCommand {

	private Long comandaId;
	private Long adrecaEnviamentId;

	public Long getComandaId() {
		return comandaId;
	}

	public void setComandaId(Long comandaId) {
		this.comandaId = comandaId;
	}

	public Long getAdrecaEnviamentId() {
		return adrecaEnviamentId;
	}

	public void setAdrecaEnviamentId(Long adrecaEnviamentId) {
		this.adrecaEnviamentId = adrecaEnviamentId;
	}
}
