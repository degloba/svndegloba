package com.degloba.casino.compres;


import com.degloba.casino.vendes.ProducteData;
import com.degloba.annotations.ValueObject;
import com.degloba.domain.sharedkernel.Money;


@ValueObject
public class OfertaItem {

	private ProducteData productData;
	
	private int quantity;
	
	private Descompte discount;
	
	private Money totalCost;
	

	public OfertaItem(ProducteData productData, int quantity) {
		this(productData, quantity, null);
	}
	
	public OfertaItem(ProducteData productData, int quantity, Descompte discount) {
		this.productData = productData;
		this.quantity = quantity;
		this.discount = discount;
		
		Money discountValue = Money.ZERO;
		if (discount != null)
			 discountValue =  discountValue.subtract(discount.getValue());
		
		this.totalCost = productData.getPrice().multiplyBy(quantity).subtract(discountValue);
	}

	public ProducteData getProductData() {
		return productData;
	}

	public Money getTotalCost() {
		return totalCost;
	}

	public Descompte getDiscount() {
		return discount;
	}
	
	public int getQuantity() {
		return quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((discount == null) ? 0 : discount.hashCode());
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
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
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