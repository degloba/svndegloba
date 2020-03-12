package com.degloba.viatges.domainpersistence.rdbms.jpa;

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

	public void addReserva(Reserva b){
		reservas.add(b);
	}

	@XmlElement(name = "Reserva", required = true, nillable = false)
	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
}
