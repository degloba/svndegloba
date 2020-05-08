package com.degloba.ecommerce.vendes.comandes.facade.dtos;

import java.util.ArrayList;
import java.util.List;

import com.degloba.persistence.rdbms.api.jpa.AggregateId;

import lombok.Getter;
import lombok.Setter;

/**
 * @category DTO (Objecte de transferencia de dades ) d'una ordre
 * 
 * Una {@link Comanda} està formada per una llista de {@link Producte}s
 * 
 * @author degloba
 *
 */
public class ComandaDto {

	@Getter @Setter private AggregateId comandaId;
	@Getter @Setter  private List<ProductesDemanatsDto> productesDemanats = new ArrayList<ProductesDemanatsDto>();
	@Getter @Setter private EstatComanda estatComanda;
	private Boolean confirmable;

}