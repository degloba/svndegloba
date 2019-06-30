package com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa;

import com.degloba.domain.annotations.ValueObject;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.ProducteData;
import com.degloba.persistence.domain.sharedkernel.Money;

@ValueObject
public class RequestItem {

	private ProducteData producteData;
	
	private int quantitat;
	
	private Money totalCost;
	
	public RequestItem(ProducteData producteData, int quantitat, Money totalCost) {
		this.producteData = producteData;
		this.quantitat = quantitat;
		this.totalCost = totalCost;
	}

	public Money getTotalCost() {
		return totalCost;
	}

	public ProducteData getProductData() {
		return producteData;
	}

	public int getQuantitat() {
		return quantitat;
	}

}
