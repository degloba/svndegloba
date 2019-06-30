package com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import com.degloba.domain.annotations.ValueObject;
import com.degloba.persistence.domain.sharedkernel.Money;


/**
 * @category Impost
 * 
 * @author degloba
 *
 */
@Embeddable
@ValueObject
public class Tax {
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "denomination", column = @Column(name = "tax_denomination")),
		@AttributeOverride(name = "currencyCode", column = @Column(name = "tax_currencyCode")) })
	private Money quantitat;

	private String description;
	
	/**
	 * For JPA only
	 */
	public Tax(){}
	
	public Tax(Money quantitat, String description) {
		super();
		this.quantitat = quantitat;
		this.description = description;
	}

	public Money getQuantitat() {
		return quantitat;
	}

	public String getDescripcio() {
		return description;
	}

	
}
