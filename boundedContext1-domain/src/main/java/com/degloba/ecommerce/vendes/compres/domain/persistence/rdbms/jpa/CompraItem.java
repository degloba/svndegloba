package com.degloba.ecommerce.sales.purchase.domain.persistence.rdbms.jpa;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;

import javax.persistence.Entity;

import com.degloba.domain.annotations.ValueObject;


import com.degloba.ecommerce.sales.productscatalog.domain.persistence.rdbms.jpa.ProductData;
import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.jpa.BaseEntity;

/**
 * Els articles comprats contenen dades copiades en cas de procediment de catàleg i canvi de descompte
 * 
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

	@Override
	public boolean existed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean notExisted() {
		// TODO Auto-generated method stub
		return false;
	}


		
}
