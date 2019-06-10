package com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa;

import com.degloba.domain.annotations.ValueObject;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.ProductData;
import com.degloba.persistence.domain.sharedkernel.Money;

@ValueObject
public class RequestItem {

	private ProductData productData;
	
	private int quantity;
	
	private Money totalCost;
	
	public RequestItem(ProductData productData, int quantity, Money totalCost) {
		this.productData = productData;
		this.quantity = quantity;
		this.totalCost = totalCost;
	}

	public Money getTotalCost() {
		return totalCost;
	}

	public ProductData getProductData() {
		return productData;
	}

	public int getQuantity() {
		return quantity;
	}

}
