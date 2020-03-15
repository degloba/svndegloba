package com.degloba.viatges.domain.persistence.rdbms.jpa;

/*import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;*/
import org.springframework.format.annotation.DateTimeFormat;

import com.degloba.viatges.util.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;



/**
 * @category Reserva d'hotel realitzada per un {@link Usuari}
 */
@XmlRootElement (name = "BookAuthors") // , namespace = DomainConstants.NAMESPACE)
@Entity
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;

	private static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

	private Long id;

	private Usuari usuari;

	private Hotel hotel;


	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date checkinDate;

	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date checkoutDate;

	private String creditCard;

	private String creditCardName;

	private int creditCardExpiryMonth;

	private int creditCardExpiryYear;

	private boolean smoking;

	private int llits;

	private Set<Comoditat> comoditats = new HashSet<Comoditat>();

	public Reserva() {
		Calendar calendar = Calendar.getInstance();
		setCheckinDate(calendar.getTime());
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		setCheckoutDate(calendar.getTime());
	}

	public Reserva(Hotel hotel, Usuari usuari) {
		this();
		this.hotel = hotel;
		this.usuari = usuari;
	}

	@XmlAttribute
	@Transient
	public BigDecimal getTotal() {
		return hotel.getPrice().multiply(new BigDecimal(getNights()));
	}

	@XmlAttribute
	@Transient
	public int getNights() {
		if (checkinDate == null || checkoutDate == null) {
			return 0;
		} else {
			return (int) (checkoutDate.getTime() - checkinDate.getTime())
					/ 1000 / 60 / 60 / 24;
		}
	}

	@XmlAttribute
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlJavaTypeAdapter( DateFormatXmlAdapter.class)
	@XmlAttribute
	@Basic
	@Temporal(TemporalType.DATE)
	public Date getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(Date datetime) {
		this.checkinDate = datetime;
	}

	@ManyToOne
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	@ManyToOne
	public Usuari getUsuari() {
		return usuari;
	}

	public void setUser(Usuari usuari) {
		this.usuari = usuari;
	}

	@XmlJavaTypeAdapter( DateFormatXmlAdapter.class)
	@XmlAttribute
	@Basic
	@Temporal(TemporalType.DATE)
	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	@XmlAttribute
	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	@Transient
	public String getDescripcio() {

		return hotel == null ? null : hotel.getNom() + ", "
				+ dateFormat.format(getCheckinDate()) + " to "
				+ dateFormat.format(getCheckoutDate());
	}

	@XmlAttribute
	public boolean isSmoking() {
		return smoking;
	}

	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}

	@XmlAttribute
	public int getLlits() {
		return llits;
	}

	public void setLlits(int llits) {
		this.llits = llits;
	}

	@XmlAttribute
	public String getCreditCardName() {
		return creditCardName;
	}

	public void setCreditCardName(String creditCardName) {
		this.creditCardName = creditCardName;
	}

	@XmlAttribute
	public int getCreditCardExpiryMonth() {
		return creditCardExpiryMonth;
	}

	public void setCreditCardExpiryMonth(int creditCardExpiryMonth) {
		this.creditCardExpiryMonth = creditCardExpiryMonth;
	}

	@XmlAttribute
	public int getCreditCardExpiryYear() {
		return creditCardExpiryYear;
	}

	public void setCreditCardExpiryYear(int creditCardExpiryYear) {
		this.creditCardExpiryYear = creditCardExpiryYear;
	}

	@Transient
	@XmlElement(name = "amenity", required = true, nillable = false)
	@XmlElementWrapper
	public Set<Comoditat> getAmenities() {
		return comoditats;
	}

	public void setAmenities(Set<Comoditat> comoditats) {
		this.comoditats = comoditats;
	}

	// TODO replace with JSR 303
	/*public void validateEnterReservaDetails(ValidationContext context) {
		MessageContext messages = context.getMessageContext();
		if (checkinDate.before(today())) {
			messages.addMessage(new MessageBuilder().error().source(
					"checkinDate").code("Reserva.checkinDate.beforeToday")
					.build());
		} else if (checkoutDate.before(checkinDate)) {
			messages.addMessage(new MessageBuilder().error().source( "checkoutDate").code( "Reserva.checkoutDate.beforeCheckinDate").build());
		}
	}*/

	private Date today() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}

	@Override
	public String toString() {
		return "Reserva(" + usuari + "," + hotel +"; from "  +dateFormat.format(getCheckinDate())+" to "+ dateFormat.format(getCheckoutDate())+ ")";
	}
}
