package com.degloba.ecommerce.sales.orders.cqrs.readmodel;

public enum OrderStatus {
	NEW, CONFIRMED, PROCESSING_PAYMENT, PAYMENT_ACCEPTED, PAYMENT_REJECTED;

}
