package com.degloba.ecommerce.crm.cqrs.readmodel;

public class ProducteDemanat {
	
	private final String orderId;
    private final String producte;
    private EstatComanda estatComanda;
 
    public ProducteDemanat(String orderId, String producte) {
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
 
    // getters, equals/hashCode and toString functions
    
    public EstatComanda getEstatComanda() {
		return estatComanda;
	}

	public void setEstatComanda(EstatComanda estatComanda) {
		this.estatComanda = estatComanda;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getProducte() {
		return producte;
	}

	public enum EstatComanda {
        PLACED, CONFIRMED, SHIPPED
    }
}

