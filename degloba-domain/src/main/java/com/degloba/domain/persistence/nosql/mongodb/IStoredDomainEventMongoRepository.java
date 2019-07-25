package com.degloba.domain.persistence.nosql.mongodb; 

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.degloba.domain.annotations.DomainRepository;
import com.degloba.event.domain.IDomainEvent;


/**
 * @author degloba
 * 
 * @category  Exten la interficie {@link MongoRepository} amb entitats de tipus {@link StoredDomainEventMongoDb}
 *
 */
@DomainRepository
public interface IStoredDomainEventMongoRepository extends MongoRepository<StoredDomainEventMongoDb, String> {


	  /**
     * Recupera una colecció d'events succeits entre un interval de dates
     *
     * @param occurredFrom Data inicial de l'event
     * @param occurredTo   Data final de l'event
     * @return Històric d'events que s’han produït en un interval de temps especificat, segons l’hora de l’aparició en ordre ascendent.
     */
    public List<StoredDomainEventMongoDb> findStoredEventsBetween(Date occurredFrom, Date occurredTo);

    /**
     * Recupera una colecció d'events succeits després d'una data
     *
     * @param occurredFrom Data inicial de l'event
     * @return Llista d'eevents que es produeixen després del temps especificat,
     * segons l’hora de l’aparició en ordre ascendent.
     */
    public List<StoredDomainEventMongoDb> findStoredEventsSince(Date occurredFrom);

    /**
     * Insert a new field events to the event store
     *
     * @param domainEvent A field event
     * @return Field events stored on behalf of the event
     */
    public StoredDomainEventMongoDb append(IDomainEvent domainEvent);

    /**
     * Close event storage
     */
    public void close();

    /**
     * Statistics of stored events
     *
     * @return Number of stored events
     */
//    public long countStoredEvents();
}
