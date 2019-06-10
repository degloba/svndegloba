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
import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.domain.ClientData;
import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;



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
	"duplicates: can not add already added product, increase quantity instead",
})

@Entity
@AggregateRoot
public class Reservation extends BaseAggregateRoot{
	
	private static final long serialVersionUID = 1L;
	

	/**
	 * @category Estat de la {@link Reservation}	 
	 *
	 */
	public enum ReservationStatus{
		OPENED, CLOSED
	}
	
		
	@Enumerated(EnumType.STRING)
	private ReservationStatus status;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
	//@JoinColumn(name = "reservation")
	///////@Fetch(FetchMode.JOIN)
    /*@JoinColumns(
    	    {@JoinColumn(name = "reservation", referencedColumnName = "aggregateId",
    	                 insertable = false, updatable = false)
    	     })*/
	private List<ReservationItem> items;

	@Embedded
	private ClientData clientData;

	@Temporal(TemporalType.DATE)
	private Date createDate;

	private Reservation() {}

	public Reservation(AggregateId aggregateId, ReservationStatus status, ClientData clientData, Date createDate){
		this.aggregateId = aggregateId;
		this.status = status;
		this.clientData = clientData;
		this.createDate = createDate;
		this.items = new ArrayList<ReservationItem>();
	}

	@Invariant({"closed", "duplicates"})
	public void add(Producte producte, int quantity){
		if (isClosed())
			domainError("Reservation already closed");
		if (!producte.isAvailabe())
			domainError("Product is no longer available");
		
		if (contains(producte)){
			increase(producte, quantity);			
		}
		else{
			addNew(producte, quantity);
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
		
		for (ReservationItem item : items) {						
			if (item.getProduct().isAvailabe()){
				Descompte descompte = descomptePolicy.applyDiscount(item.getProduct(), item.getQuantity(), item.getProduct().getPrice());
				OfertaItem ofertaItem = new OfertaItem(item.getProduct().generateSnapshot(), item.getQuantity(), descompte);
				
				availabeItems.add(ofertaItem);
			}
			else {
				OfertaItem ofertaItem = new OfertaItem(item.getProduct().generateSnapshot(), item.getQuantity());
				
				unavailableItems.add(ofertaItem);
			}
		}
		
		return new Oferta(availabeItems, unavailableItems);
	}

	/**
	 * @category Afegeix una nou {@link Producte} i una quantitat
	 * 
	 * @param producte
	 * @param quantity
	 */
	private void addNew(Producte producte, int quantity) {
		ReservationItem item = new ReservationItem(producte, quantity);
		items.add(item);
	}

	/**
	 * @category Incrementa a un {@link Producte}, una quantitat
	 * 
	 * @param producte
	 * @param quantity
	 */
	private void increase(Producte producte, int quantity) {
		for (ReservationItem item : items) {
			if (item.getProduct().equals(producte)){
				item.changeQuantityBy(quantity);
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
		for (ReservationItem item : items) {
			if (item.getProduct().equals(producte))
				return true;
		}
		return false;
	}

	public boolean isClosed() {
		return status.equals(ReservationStatus.CLOSED);
	}
	
	@Invariant({"closed"})
	public void close(){
		if (isClosed())
			domainError("Reservation is already closed");
		status = ReservationStatus.CLOSED;
	}

	public List<ProducteReservat> getReservedProducts() {
		ArrayList<ProducteReservat> result = new ArrayList<ProducteReservat>(items.size());
		
		for (ReservationItem item : items) {
			result.add(new ProducteReservat(item.getProduct().getAggregateId(), item.getProduct().getName(), item.getQuantity(), calculateItemCost(item)));
		}
		
		return result;
	}
	
	private Money calculateItemCost(ReservationItem item){
		return item.getProduct().getPrice().multiplyBy(item.getQuantity());
	}

	

	public ClientData getClientData() {
		return clientData;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public ReservationStatus getStatus() {
		return status;
	}

	
	
}
