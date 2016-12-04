package com.degloba.ecommerce.sales.purchase.domain.persistence.rdbms.jpa;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.degloba.domain.annotations.ValueObject;

import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.domain.persistence.rdbms.jpa.BaseEntity;
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.domain.sharedkernel.Money;
import com.degloba.ecommerce.sales.productscatalog.domain.persistence.rdbms.jpa.ProductData;

/**
 * Models purchased items - contains copied data in case on catalog proces and discount change 
 * @author degloba
 *
 */
@ValueObject
@Entity
public class PurchaseItem extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
