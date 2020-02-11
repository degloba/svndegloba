package com.degloba.ecommerce.vendes.facturacio.domain.services;

import javax.inject.Inject;

import com.degloba.domain.annotations.DomainService;
import com.degloba.ecommerce.vendes.facturacio.domain.factories.FacturesFactory;
import com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa.Factura;
import com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa.LiniaFacturacio;
import com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa.PeticioFactura;
import com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa.RequestItem;
import com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa.Tax;
import com.degloba.ecommerce.vendes.facturacio.domain.policies.IImpostPolicy;
import com.degloba.persistence.domain.sharedkernel.Money;


/**
 * @category 
 * <ul>
 * <li> Does not have a natural place in any aggregate - we don't want to bloat Order with issuance of the Invoice
 * <li> Has broad dependencies - we don't want Order to become a 'God Class'
 * <li> Is used only (or not many) in one Use Case/user Story so is not essential for any Aggregate
 * <ul>
 * 
 * @author degloba
 *
 */
@DomainService
public class BookKeeperService {
	

	@Inject
	private FacturesFactory facturesFactory;
	
	/**
	 * @category emet una factura
	 * 
	 * @param peticioFactura
	 * @param impostPolicy
	 * @return
	 */
	public Factura emet(PeticioFactura peticioFactura, IImpostPolicy impostPolicy){
		Factura factura = facturesFactory.create(peticioFactura.getClientData());
		
		for (RequestItem item : peticioFactura.getItems()){
			Money net = item.getTotalCost();			
			Tax tax = impostPolicy.calculaImpost(item.getProductData().getType(), net);			
						
			LiniaFacturacio liniaFacturacio = new LiniaFacturacio(item.getProductData(), item.getQuantitat(), net, tax);			
			factura.addItem(liniaFacturacio);
		}
		
		return factura;
	}
	
}
