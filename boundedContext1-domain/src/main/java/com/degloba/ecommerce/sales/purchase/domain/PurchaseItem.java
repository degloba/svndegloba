package com.degloba.ecommerce.sales.purchase.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import com.degloba.annotations.ValueObject;
import com.degloba.domain.BaseEntity;
import com.degloba.ecommerce.sales.productscatalog.domain.ProductData;
import com.degloba.domain.sharedkernel.Money;

/**
 * Models purchased items - contains copied data in case on catalog proces and discount change 
 * @author degloba
 *
 */
@ValueObject
@Entity
public class PurchaseItem extends BaseEntity{
	
	@Embedded
	private ProductData productData;
	
	private int quantity;	
	
	@AttributeOverrides({
		@AttributeOverride(name = "denomination", column = @Column(name = "purchaseTotalCost_denomination")),
		@AttributeOverride(name = "currencyCode", column = @Column(name = "purchaseTotalCost_currencyCode")) })
	private Money totalCost;
	
	@SuppressWarnings("unused")
	private PurchaseItem() {}
	
	public PurchaseItem(ProductData productData, int quantity, Money totalCost) {
		this.productData = productData;
		this.quantity = quantity;
		this.totalCost = totalCost;
	}

	public int getQuantity() {
		return quantity;
	}

	public ProductData getProductData() {
		return productData;
	}

	public Money getTotalCost() {
		return totalCost;
	}

	
}
