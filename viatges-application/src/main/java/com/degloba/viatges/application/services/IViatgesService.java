package com.degloba.viatges.application.services;

import com.degloba.viatges.application.SearchCriteria;
import com.degloba.viatges.domain.persistence.rdbms.jpa.Hotel;
import com.degloba.viatges.domain.persistence.rdbms.jpa.Reserva;
import com.degloba.viatges.domain.persistence.rdbms.jpa.Usuari;

import java.util.List;

/**
 * @category Un servei que serveix per recuperar {@link Hotel}s i {@link Reserva}s des d'un repository. 
 * També suporta la possibilitat de cancelar una {@link Reserva}.
 */
public interface IViatgesService {


    /**
     * @param username el nom d'usuari
     * @return {@link Usuari}
     */
    Usuari buscarUsuari(String username);

    Usuari login(String u, String pw);

    /**
     * Busca {@link Reserves} fets per un {@link Usuari}
     *
     * @param username el nom de l'{@link Usuari}
     * @return els seus {@link Reserves}
     */
    List<Reserva> buscarReserves(String username);

    Reserva buscarReservaById(Long id);

    /**
     * Busca una llista d'hotels accessibles per reservar a partir d'un criteri.
     *
     * @param criteria el criteri de cerca
     * @return una llista de {@link Hotel}s que cumpleix el criteri
     */
    List<Hotel> buscarHotels(SearchCriteria criteria);

    /**
     * Busca l'hotel pel seu identificador.
     *
     * @param id the hotel id
     * @return {@link Hotel}
     */
    Hotel buscarHotelById(Long id);

    /**
     * Crea un nou, transient hotel Reserva instance for the given user.
     *
     * @param hotelId  the hotelId
     * @param userName the user name
     * @return the new transient Reserva instance
     */
    Reserva creaReserva(Long hotelId, String userName);

    /**
     * Cancel.la un {@link Reserva} existent.
     *
     * @param id l'id de {@link Reserva}
     */
    void cancelaReserva(Long id);

    /**
     * persists the detached Reserva object
     *
     * @param bo Reserva to persist
     */
    void persistReserva(Reserva reserva);
}

