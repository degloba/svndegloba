package com.degloba.ecommerce.sales.reservation;

import com.degloba.annotations.DomainRepository;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.google.appengine.api.datastore.Key;

@DomainRepository
public interface IReservationRepository {

	void save(Reservation reservation);

	Reservation load(Key reservationId);
}
