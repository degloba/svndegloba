package com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa;

import com.degloba.domain.annotations.ValueObject;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.ProducteData;
import com.degloba.persistence.domain.sharedkernel.Money;

/**
 * @category 
 * 
 * @author degloba
 *
 */
@ValueObject
public class OfertaItem {

	private ProducteData producteData;
	
	private int quantitat;
	
	private Descompte descompte;
	
	private Money totalCost;
	

	public OfertaItem(ProducteData producteData, int quantitat) {
		this(producteData, quantitat, null);
	}
	
	public OfertaItem(ProducteData producteData, int quantitat, Descompte descompte) {
		this.producteData = producteData;
		this.quantitat = quantitat;
		this.descompte = descompte;
		
		Money discountValue = Money.ZERO;
		if (descompte != null)
			 discountValue =  discountValue.subtract(descompte.getValue());
		
		this.totalCost = producteData.getPreu().multiplyBy(quantitat).subtract(discountValue);
	}

	public ProducteData getProductData() {
		return producteData;
	}

	public Money getTotalCost() {
		return totalCost;
	}

	public Descompte getDiscount() {
		return descompte;
	}
	
	public int getQuantitat() {
		return quantitat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descompte == null) ? 0 : descompte.hashCode());
		result = prime * result
				+ ((producteData == null) ? 0 : producteData.hashCode());
		result = prime * result + quantitat;
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
		if (producteData == null) {
			if (other.producteData != null)
				return false;
		} else if (!producteData.equals(other.producteData))
			return false;
		if (quantitat != other.quantitat)
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
		if (! producteData.equals(item.producteData))
			return false;
		
		if (quantitat != item.quantitat)
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
