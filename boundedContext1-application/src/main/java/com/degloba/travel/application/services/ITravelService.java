package com.degloba.travel.application.services;

import com.degloba.travel.application.SearchCriteria;
import com.degloba.viatges.domain.persistence.rdbms.jpa.Reserva;
import com.degloba.viatges.domain.persistence.rdbms.jpa.Hotel;
import com.degloba.viatges.domain.persistence.rdbms.jpa.Usuari;

import java.util.List;

/**
 * @category Un servei que serveix per recuperar {@link Hotel}s i {@link Reserves} des d'un repository. 
 * També suporta la possibilitat de cancelar un {@link Reserva}.
 */
public interface ITravelService {


    /**
     * @param username el nom d'usuari
     * @return {@link Usuari}
     */
    Usuari findUser(String username);

    Usuari login(String u, String pw);

    /**
     * Busca {@link Reserves} fets per un {@link Usuari}
     *
     * @param username el nom de l'{@link Usuari}
     * @return els seus {@link Reserves}
     */
    List<Reserva> findBookings(String username);

    Reserva findBookingById(Long id);

    /**
     * Busca una llista d'hotels accessibles per reservar a partir d'un criteri.
     *
     * @param criteria el criteri de cerca
     * @return una llista de {@link Hotel}s que cumpleix el criteri
     */
    List<Hotel> findHotels(SearchCriteria criteria);

    /**
     * Busca l'hotel pel seu identificador.
     *
     * @param id the hotel id
     * @return {@link Hotel}
     */
    Hotel findHotelById(Long id);

    /**
     * Crea un nou, transient hotel booking instance for the given user.
     *
     * @param hotelId  the hotelId
     * @param userName the user name
     * @return the new transient booking instance
     */
    Reserva createBooking(Long hotelId, String userName);

    /**
     * Cancel.la un {@link Reserva} existent.
     *
     * @param id l'id de {@link Reserva}
     */
    void cancelBooking(Long id);

    /**
     * persists the detached booking object
     *
     * @param bo booking to persist
     */
    void persistBooking(Reserva bo);
}

