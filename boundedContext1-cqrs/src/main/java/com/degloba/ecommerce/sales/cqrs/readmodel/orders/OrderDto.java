package com.degloba.ecommerce.sales.cqrs.readmodel.orders;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Key;


public class OrderDto {

	private long orderId;
	private List<OrderedProductDto> orderedProducts = new ArrayList<OrderedProductDto>();
	private OrderStatus status;
	private Boolean confirmable;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public List<OrderedProductDto> getOrderedProducts() {
		return orderedProducts;
	}

	public void setOrderedProducts(List<OrderedProductDto> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Boolean getConfirmable() {
		return confirmable;
	}

	public void setConfirmable(Boolean confirmable) {
		this.confirmable = confirmable;
	}
}