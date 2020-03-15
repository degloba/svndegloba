package com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa;

import java.util.ArrayList;
import java.util.List;

import com.degloba.domain.annotations.ValueObject;
import com.degloba.persistence.rdbms.jpa.AggregateId;



/**
 * Oferta disponible per client (incloent disponibilitat i descomptes)
 *   
 * @author degloba
 *
 */
@ValueObject
public class Oferta {

	private List<OfertaItem> ofertesDisponibles = new ArrayList<OfertaItem>();
	
	private List<OfertaItem> ofertesNoDisponibles = new ArrayList<OfertaItem>();
	
	
	public Oferta(List<OfertaItem> availabeItems, List<OfertaItem> unavailableItems) {
		this.ofertesDisponibles = availabeItems;
		this.ofertesNoDisponibles = unavailableItems;
	}

	public List<OfertaItem> obtenirArticlesDisponibles() {
		return ofertesDisponibles;
	}
	
	public List<OfertaItem> getUnavailableItems() {
		return ofertesNoDisponibles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ofertesDisponibles == null) ? 0 : ofertesDisponibles.hashCode());
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
		if (ofertesDisponibles == null) {
			if (other.ofertesDisponibles != null)
				return false;
		} else if (!ofertesDisponibles.equals(other.ofertesDisponibles))
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
		if (! (ofertesDisponibles.size() == seenOffer.ofertesDisponibles.size()))
			return false;
		
		for (OfertaItem item : ofertesDisponibles) {
			OfertaItem sameItem = seenOffer.findItem(item.getProductData().getProducteId());
			if (sameItem == null)
				return false;
			if (!sameItem.sameAs(item, delta))
				return false;
		}
		
		return true;
	}

	private OfertaItem findItem(AggregateId producteId) {
		for (OfertaItem item : ofertesDisponibles){
			if (item.getProductData().getProducteId() == producteId)
				return item;
		}
		return null;
	}
	
	
}
