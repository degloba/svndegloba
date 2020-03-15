package com.degloba.eventsourcing.persona;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;


import com.degloba.persones.cqrs.querys.AdrecaByIdQuery;
import com.degloba.persones.cqrs.querys.AdrecaByIdQueryResult;

import javax.persistence.EntityManager;

/**
 * @author degloba
 *
 * @category {@link org.} de {@link Adreca} basat en Axon
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AdrecaQueryHandler {

    private final EntityManager entityManager;

    @QueryHandler
    public AdrecaByIdQueryResult handle(AdrecaByIdQuery query) {
        Adreca adreca = entityManager.find(Adreca.class, query.getAddressId());
        return new AdrecaByIdQueryResult(adreca.getAdrecaId(),
                adreca.getPersonaId(), adreca.getCarrerINumero(), adreca.getZipCode());
    }

}
