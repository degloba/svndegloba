package com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa;

import com.degloba.domain.annotations.ValueObject;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.ProductData;
import com.degloba.persistence.domain.sharedkernel.Money;

@ValueObject
public class OfertaItem {

	private ProductData productData;
	
	private int quantity;
	
	private Descompte descompte;
	
	private Money totalCost;
	

	public OfertaItem(ProductData productData, int quantity) {
		this(productData, quantity, null);
	}
	
	public OfertaItem(ProductData productData, int quantity, Descompte descompte) {
		this.productData = productData;
		this.quantity = quantity;
		this.descompte = descompte;
		
		Money discountValue = Money.ZERO;
		if (descompte != null)
			 discountValue =  discountValue.subtract(descompte.getValue());
		
		this.totalCost = productData.getPrice().multiplyBy(quantity).subtract(discountValue);
	}

	public ProductData getProductData() {
		return productData;
	}

	public Money getTotalCost() {
		return totalCost;
	}

	public Descompte getDiscount() {
		return descompte;
	}
	
	public int getQuantity() {
		return quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descompte == null) ? 0 : descompte.hashCode());
		result = prime * result
				+ ((productData == null) ? 0 : productData.hashCode());
		result = prime * result + quantity;
		result = prime * result
				+ ((totalCost == null) ? 0 : totalCost.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfertaItem other = (OfertaItem) obj;
		if (descompte == null) {
			if (other.descompte != null)
				return false;
		} else if (!descompte.equals(other.descompte))
			return false;
		if (productData == null) {
			if (other.productData != null)
				return false;
		} else if (!productData.equals(other.productData))
			return false;
		if (quantity != other.quantity)
			return false;
		if (totalCost == null) {
			if (other.totalCost != null)
				return false;
		} else if (!totalCost.equals(other.totalCost))
			return false;
		return true;
	}

	/**
	 * 
	 * @param item
	 * @param delta acceptable percentage difference 
	 * @return
	 */
	public boolean sameAs(OfertaItem item, double delta) {
		if (! productData.equals(item.productData))
			return false;
		
		if (quantity != item.quantity)
			return false;
		
		
		Money max, min;
		if (totalCost.greaterThan(item.totalCost)){
			max = totalCost;
			min = item.totalCost;
		}
		else{
			max = item.totalCost;
			min = totalCost;
		}
		
		Money difference = max.subtract(min);
		Money acceptableDelta = max.multiplyBy(delta / 100); 
		
		return acceptableDelta.greaterThan(difference);
	}

	
	


}
