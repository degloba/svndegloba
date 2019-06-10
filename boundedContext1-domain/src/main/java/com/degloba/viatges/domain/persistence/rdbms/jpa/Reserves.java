package com.degloba.viatges.domain.persistence.rdbms.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * Reserves
 */
@XmlRootElement
public class Reserves {
	private List<Reserva> reservas ;

	public Reserves() {
		reservas = new ArrayList<Reserva>();
	}

	public Reserves( Collection<Reserva> reservas) {
		this.reservas = new ArrayList<Reserva>(reservas);
	}

	public void addBooking(Reserva b){
		reservas.add(b);
	}

	@XmlElement(name = "booking", required = true, nillable = false)
	public List<Reserva> getBookings() {
		return reservas;
	}

	public void setBookings(List<Reserva> reservas) {
		this.reservas = reservas;
	}
}
