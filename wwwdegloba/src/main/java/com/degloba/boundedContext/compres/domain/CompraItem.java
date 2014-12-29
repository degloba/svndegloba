package com.degloba.boundedContext.compres.domain;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;



import com.degloba.boundedContext.vendes.domain.ProducteData;
import com.degloba.sharedkernel.Money;

import domain.annotations.ValueObject;
import domain.support.BaseEntity;



/**
 * Models purchased items - contains copied data in case on catalog proces and discount change 
 * @author Slawek
 *
 */
@ValueObject
@Entity
public class CompraItem extends BaseEntity{
	
	@Embedded
	private ProducteData productData;
	
	private int quantity;	
	
	@AttributeOverrides({
		@AttributeOverride(name = "denomination", column = @Column(name = "purchaseTotalCost_denomination")),
		@AttributeOverride(name = "currencyCode", column = @Column(name = "purchaseTotalCost_currencyCode")) })
	private Money totalCost;
	
	protected CompraItem() {}
	
	public CompraItem(ProducteData productData, int quantity, Money totalCost) {
		this.productData = productData;
		this.quantity = quantity;
		this.totalCost = totalCost;
	}

	public int getQuantity() {
		return quantity;
	}

	public ProducteData getProductData() {
		return productData;
	}

	public Money getTotalCost() {
		return totalCost;
	}

	@Override
	public Serializable getId() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
