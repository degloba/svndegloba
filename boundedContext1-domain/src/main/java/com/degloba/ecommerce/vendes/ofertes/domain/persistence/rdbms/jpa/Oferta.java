package com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa;

import java.util.ArrayList;
import java.util.List;

import com.degloba.domain.annotations.ValueObject;
import com.degloba.persistence.domain.AggregateId;


/**
 * Oferta disponible per client (incloent disponibilitat i descomptes)
 *   
 * @author degloba
 *
 */
@ValueObject
public class Oferta {

	private List<OfertaItem> availableItems = new ArrayList<OfertaItem>();
	
	private List<OfertaItem> unavailableItems = new ArrayList<OfertaItem>();
	
	
	public Oferta(List<OfertaItem> availabeItems, List<OfertaItem> unavailableItems) {
		this.availableItems = availabeItems;
		this.unavailableItems = unavailableItems;
	}

	public List<OfertaItem> getAvailableItems() {
		return availableItems;
	}
	
	public List<OfertaItem> getUnavailableItems() {
		return unavailableItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((availableItems == null) ? 0 : availableItems.hashCode());
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
		Oferta other = (Oferta) obj;
		if (availableItems == null) {
			if (other.availableItems != null)
				return false;
		} else if (!availableItems.equals(other.availableItems))
			return false;
		return true;
	}

	/**
	 * 
	 * @param seenOffer
	 * @param delta acceptable difference in percent
	 * @return
	 */
	public boolean sameAs(Oferta seenOffer, double delta) {
		if (! (availableItems.size() == seenOffer.availableItems.size()))
			return false;
		
		for (OfertaItem item : availableItems) {
			OfertaItem sameItem = seenOffer.findItem(item.getProductData().getProductId());
			if (sameItem == null)
				return false;
			if (!sameItem.sameAs(item, delta))
				return false;
		}
		
		return true;
	}

	private OfertaItem findItem(AggregateId productId) {
		for (OfertaItem item : availableItems){
			if (item.getProductData().getProductId() == productId)
				return item;
		}
		return null;
	}
	
	
}
