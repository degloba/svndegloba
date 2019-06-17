package com.degloba.ecommerce.enviaments.cqrs.readmodel.dtos;

import java.io.Serializable;

import com.degloba.ecommerce.enviaments.domain.EstatEnviament;

/**
 * 
 * @author degloba
 *
 * @category Data Transfer Object (DTO) d'una entitat {@link Enviament}
 */
@SuppressWarnings("serial")
public class EnviamentDto implements Serializable {

    private long shipmentId;
    private long orderId;
    private EstatEnviament status;

    public EnviamentDto(long shipmentId, long orderId, EstatEnviament status) {
        this.shipmentId = shipmentId;
        this.orderId = orderId;
        this.status = status;
    }

    public long getShipmentId() {
        return shipmentId;
    }

    public long getOrderId() {
        return orderId;
    }

    public EstatEnviament getStatus() {
        return status;
    }
}
