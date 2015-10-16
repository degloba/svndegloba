package com.degloba.ecommerce.sales.offer.domain;

import java.util.ArrayList;
import java.util.List;

import com.degloba.annotations.ValueObject;
import com.google.appengine.api.datastore.Key;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;

/**
 * Offer that is available per client (including availability and discounts)
 *   
 * @author degloba
 *
 */
@ValueObject
public class Offer {

	private List<OfferItem> availabeItems = new ArrayList<OfferItem>();
	
	private List<OfferItem> unavailableItems = new ArrayList<OfferItem>();
	
	
	public Offer(List<OfferItem> availabeItems, List<OfferItem> unavailableItems) {
		this.availabeItems = availabeItems;
		this.unavailableItems = unavailableItems;
	}

	public List<OfferItem> getAvailabeItems() {
		return availabeItems;
	}
	
	public List<OfferItem> getUnavailableItems() {
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
		Offer other = (Offer) obj;
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
	public boolean sameAs(Offer seenOffer, double delta) {
		if (! (availabeItems.size() == seenOffer.availabeItems.size()))
			return false;
		
		for (OfferItem item : availabeItems) {
			OfferItem sameItem = seenOffer.findItem(item.getProductData().getProductId());
			if (sameItem == null)
				return false;
			if (!sameItem.sameAs(item, delta))
				return false;
		}
		
		return true;
	}

	private OfferItem findItem(Key productId) {
		for (OfferItem item : availabeItems){
			if (item.getProductData().getProductId().equals(productId))
				return item;
		}
		return null;
	}
	
	
}
