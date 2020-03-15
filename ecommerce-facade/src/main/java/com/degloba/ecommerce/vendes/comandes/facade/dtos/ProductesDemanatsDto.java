package com.degloba.ecommerce.vendes.comandes.facade.dtos;

import com.degloba.persistence.rdbms.jpa.AggregateId;

import lombok.Getter;
import lombok.Setter;


/** 
 * @category Defineix un producte d'una {@link Commanda}
 * 
 * @author degloba
 */
public class ProductesDemanatsDto {
	@Getter @Setter private AggregateId offerId;
	

}