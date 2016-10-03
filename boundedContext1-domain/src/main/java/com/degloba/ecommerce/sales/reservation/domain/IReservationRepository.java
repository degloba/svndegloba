package com.degloba.ecommerce.sales.reservation.domain;

import com.degloba.domain.annotations.DomainRepository;


@DomainRepository
public interface IReservationRepository {

	void save(Reservation reservation);

	Reservation load(long reservationId);

	Reservation load(Class<Reservation> class1, long orderId);
}
