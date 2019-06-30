package com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;*/

import com.degloba.domain.annotations.AggregateRoot;
import com.degloba.domain.annotations.Function;
import com.degloba.domain.annotations.Invariant;
import com.degloba.domain.annotations.InvariantsList;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa.Descompte;
import com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa.Oferta;
import com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa.OfertaItem;
import com.degloba.ecommerce.vendes.ofertes.domain.policies.DescomptePolicy;

import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.jpa.ClientData;



/**
 * @author degloba
 * 
 * @category 
 * <ul>
 * <li>La reserva és només una "llista de desitjos".
 * </li>
 * <li> 
 * El sistema no pot garantir que l'usuari pugui comprar els productes desitjats. </br>
 * </li>
 * <li>
 * La reserva genera {@link Oferta} , que es calcula segons els preus actuals i la disponibilitat actual.
 * </li>
 * 
 *
 */

@InvariantsList({
	"closed: closed reservation cano not be modified",
	"duplicates: can not add already added product, increase quantitat instead",
})

@Entity
@AggregateRoot
public class Reserva extends BaseAggregateRoot{
	
	private static final long serialVersionUID = 1L;
	

	/**
	 * @category Estat de la {@link Reserva}	 
	 *
	 */
	public enum EstatReserva{
		OPENED, CLOSED
	}
	
		
	@Enumerated(EnumType.STRING)
	private EstatReserva status;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
	//@JoinColumn(name = "reservation")
	///////@Fetch(FetchMode.JOIN)
    /*@JoinColumns(
    	    {@JoinColumn(name = "reservation", referencedColumnName = "aggregateId",
    	                 insertable = false, updatable = false)
    	     })*/
	private List<ReservaItem> items;

	@Embedded
	private ClientData clientData;

	@Temporal(TemporalType.DATE)
	private Date createDate;

	private Reserva() {}

	public Reserva(AggregateId aggregateId, EstatReserva status, ClientData clientData, Date createDate){
		this.aggregateId = aggregateId;
		this.status = status;
		this.clientData = clientData;
		this.createDate = createDate;
		this.items = new ArrayList<ReservaItem>();
	}

	@Invariant({"closed", "duplicates"})
	public void add(Producte producte, int quantitat){
		if (isClosed())
			domainError("Reservation already closed");
		if (!producte.isAvailabe())
			domainError("Product is no longer available");
		
		if (contains(producte)){
			increase(producte, quantitat);			
		}
		else{
			addNew(producte, quantitat);
		}
	}
	
	/**
	 * Sample function closured by policy </br> 
	 * Higher order function closured by policy function</br>
	 * </br>
	 * La funció carrega els preus actuals i prepara l'oferta segons la disponibilitat actual i el descompte donat
	 * @param descomptePolicy
	 * @return
	 */
	@Function
	public Oferta calculateOffer(DescomptePolicy descomptePolicy) {
		List<OfertaItem> availabeItems = new ArrayList<OfertaItem>();
		List<OfertaItem> unavailableItems = new ArrayList<OfertaItem>();
		
		for (ReservaItem item : items) {						
			if (item.getProducte().isAvailabe()){
				Descompte descompte = descomptePolicy.applyDiscount(item.getProducte(), item.getQuantitat(), item.getProducte().getPreu());
				OfertaItem ofertaItem = new OfertaItem(item.getProducte().generateSnapshot(), item.getQuantitat(), descompte);
				
				availabeItems.add(ofertaItem);
			}
			else {
				OfertaItem ofertaItem = new OfertaItem(item.getProducte().generateSnapshot(), item.getQuantitat());
				
				unavailableItems.add(ofertaItem);
			}
		}
		
		return new Oferta(availabeItems, unavailableItems);
	}

	/**
	 * @category Afegeix una nou {@link Producte} i una quantitat
	 * 
	 * @param producte
	 * @param quantitat
	 */
	private void addNew(Producte producte, int quantitat) {
		ReservaItem item = new ReservaItem(producte, quantitat);
		items.add(item);
	}

	/**
	 * @category Incrementa a un {@link Producte}, una quantitat
	 * 
	 * @param producte
	 * @param quantitat
	 */
	private void increase(Producte producte, int quantitat) {
		for (ReservaItem item : items) {
			if (item.getProducte().equals(producte)){
				item.changeQuantityBy(quantitat);
				break;
			}
		}
	}

	/**
	 * @category Retorna True si existeix el producte i False si no existeix 
	 * @param producte
	 * @return
	 */
	public boolean contains(Producte producte) {
		for (ReservaItem item : items) {
			if (item.getProducte().equals(producte))
				return true;
		}
		return false;
	}

	public boolean isClosed() {
		return status.equals(EstatReserva.CLOSED);
	}
	
	@Invariant({"closed"})
	public void close(){
		if (isClosed())
			domainError("Reservation is already closed");
		status = EstatReserva.CLOSED;
	}

	public List<ProducteReservat> getReservedProducts() {
		ArrayList<ProducteReservat> result = new ArrayList<ProducteReservat>(items.size());
		
		for (ReservaItem item : items) {
			result.add(new ProducteReservat(item.getProducte().getAggregateId(), item.getProducte().getName(), item.getQuantitat(), calculateItemCost(item)));
		}
		
		return result;
	}
	
	private Money calculateItemCost(ReservaItem item){
		return item.getProducte().getPreu().multiplyBy(item.getQuantitat());
	}

	

	public ClientData getClientData() {
		return clientData;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public EstatReserva getStatus() {
		return status;
	}

	
	
}
