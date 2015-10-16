package com.degloba.ecommerce.sales.reservation.domain;

import com.degloba.annotations.DomainRepository;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.google.appengine.api.datastore.Key;

@DomainRepository
public interface IReservationRepository {

	void save(Reservation reservation);

	Reservation load(Key reservationId);

	Reservation load(Class<Reservation> class1, Key orderId);
}
