package com.degloba.ecommerce.crm.facade.dtos;

import lombok.Data;

/**
 * 
 * @author degloba
 * 
 * @category Producte demanat
 *
 */
@Data
public class ProducteDemanatDto {
	
	private final String orderId;
    private final String producte;
    private EstatComanda estatComanda;
 
    public ProducteDemanatDto(String orderId, String producte) {
        this.orderId = orderId;
        this.producte = producte;
        setEstatComanda(EstatComanda.PLACED);
    }
 
    public void setOrderConfirmed() {
        this.setEstatComanda(EstatComanda.CONFIRMED);
    }
 
    public void setOrderShipped() {
        this.setEstatComanda(EstatComanda.SHIPPED);
    }
 
    
	public enum EstatComanda {
        PLACED, CONFIRMED, SHIPPED
    }
}

