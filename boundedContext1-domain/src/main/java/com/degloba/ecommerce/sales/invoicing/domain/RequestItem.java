package com.degloba.ecommerce.sales.invoicing.domain;

import com.degloba.annotations.ValueObject;
import com.degloba.ecommerce.sales.productscatalog.domain.ProductData;
import com.degloba.domain.sharedkernel.Money;

@ValueObject
class RequestItem {

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
