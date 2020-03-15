package com.degloba.ecommerce.eventsourcing.impl.eventuate;

public enum OrderState {
	REJECTED,APPROVED, CREATED, NEW, CONFIRMED, PROCESSING_PAYMENT, PAYMENT_ACCEPTED, PAYMENT_REJECTED;

}
