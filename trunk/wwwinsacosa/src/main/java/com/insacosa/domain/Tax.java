package com.insacosa.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import ddd.domain.annotations.ValueObject;
import ddd.domain.sharedkernel.Money;

/**
 * 
 * @author Slawek
 *
 */
@Embeddable
@ValueObject
public class Tax {
	
	@Embedded
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
