package com.degloba.boundedContext.compres.domain;

import java.util.ArrayList;
import java.util.List;

import domain.annotations.ValueObject;
import domain.canonicalmodel.publishedlanguage.AggregateId;



/**
 * Offer that is available per client (including availability and discounts)
 *   
 * @author Slawek
 *
 */
@ValueObject
public class Oferta {

	private List<OfertaItem> availabeItems = new ArrayList<OfertaItem>();
	
	private List<OfertaItem> unavailableItems = new ArrayList<OfertaItem>();
	
	
	public Oferta(List<OfertaItem> availabeItems, List<OfertaItem> unavailableItems) {
		this.availabeItems = availabeItems;
		this.unavailableItems = unavailableItems;
	}

	public List<OfertaItem> getAvailabeItems() {
		return availabeItems;
	}
	
	public List<OfertaItem> getUnavailableItems() {
		return unavailableItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((availabeItems == null) ? 0 : availabeItems.hashCode());
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
		if (availabeItems == null) {
			if (other.availabeItems != null)
				return false;
		} else if (!availabeItems.equals(other.availabeItems))
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
		if (! (availabeItems.size() == seenOffer.availabeItems.size()))
			return false;
		
		for (OfertaItem item : availabeItems) {
			OfertaItem sameItem = seenOffer.findItem(item.getProductData().getProductId());
			if (sameItem == null)
				return false;
			if (!sameItem.sameAs(item, delta))
				return false;
		}
		
		return true;
	}

	private OfertaItem findItem(AggregateId productId) {
		for (OfertaItem item : availabeItems){
			if (item.getProductData().getProductId().equals(productId))
				return item;
		}
		return null;
	}
	
	
}
