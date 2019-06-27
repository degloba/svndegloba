package com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.dtos;

import java.util.ArrayList;
import java.util.List;

import com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.OrdreStatus;
import com.degloba.persistence.domain.AggregateId;

/**
 * 
 * @author degloba
 * 
 * @category DTO (Objecte de transferencia de dades ) d'una ordre
 * 
 * Una ordre està formada per una llista de productes
 *
 */
public class OrderDto {

	private AggregateId orderId;
	private List<OrderedProductDto> orderedProducts = new ArrayList<OrderedProductDto>();
	private OrdreStatus status;
	private Boolean confirmable;

	public AggregateId getOrderId() {
		return orderId;
	}

	public void setOrderId(AggregateId orderId) {
		this.orderId = orderId;
	}

	public List<OrderedProductDto> getOrderedProducts() {
		return orderedProducts;
	}

	public void setOrderedProducts(List<OrderedProductDto> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

	public OrdreStatus getStatus() {
		return status;
	}

	public void setStatus(OrdreStatus status) {
		this.status = status;
	}

	public Boolean getConfirmable() {
		return confirmable;
	}

	public void setConfirmable(Boolean confirmable) {
		this.confirmable = confirmable;
	}
}