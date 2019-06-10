package com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import com.degloba.domain.annotations.ValueObject;
import com.degloba.persistence.domain.sharedkernel.Money;


/**
 * Impost
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
	private Money amount;

	private String description;
	
	/**
	 * For JPA only
	 */
	public Tax(){}
	
	public Tax(Money amount, String description) {
		super();
		this.amount = amount;
		this.description = description;
	}

	public Money getAmount() {
		return amount;
	}

	public String getDescription() {
		return description;
	}

	
}
