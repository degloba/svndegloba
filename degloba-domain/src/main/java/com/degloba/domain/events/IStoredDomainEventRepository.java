package com.degloba.domain.events;

import java.util.Date;
import java.util.List;

import com.degloba.domain.annotations.DomainRepository;
import com.degloba.events.api.IDomainEvent;
import com.degloba.events.persistence.IDomainEventStore;


/**
 * @category Repositori d'events d'entitats de domini
 * 
 * @author degloba
 *
 */
@DomainRepository
public interface IStoredDomainEventRepository {

	 /**
     * Recupera una llista d'events succeits en un interval de temps. 
     *
     * @param occurredFrom Data inicial de l'event
     * @param occurredTo   Data final de l'event
     * @return Una llista d’events que s’han produït en un interval de temps especificat, segons l’hora de l’aparició en ordre ascendent.
     */
    public List<IDomainEventStore> trobaEventsDominiGuardatsEntre(Date occurredFrom, Date occurredTo);

    /**
     * Recupera una llista d'events que han succeit després d'una data.
     *
     * @param occurredFrom Data inicial de l'event
     * @return Its collection of events that occur after the specified time, 
     * according to time of occurrence in ascending order.
     */
    public List<IDomainEventStore> trobaEventDominiGuardatsDesde(Date occurredFrom);

    /**
     * Insert a new field events to the event store
     *
     * @param domainEventStore A field event
     * @return Field events stored on behalf of the event
     */
    public IDomainEventStore append(DomainEvent domainEventStore);

    /**
     * Tanca el magatzem d'events
     */
    public void close();

    /**
     * Estadístiques dels events guardats
     *
     * @return Número d'events guardats
     */
//    public long countStoredEvents();
}
