package com.degloba.ecommerce.enviaments.application.eventlisteners;

import javax.inject.Inject;

import com.degloba.ecommerce.enviaments.domain.events.ComandaEnviadaEvent;
import com.degloba.ecommerce.enviaments.domain.factories.EnviamentsFactory;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.Enviament;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.IEnviamentsRepository;
import com.degloba.ecommerce.vendes.comandes.cqrs.queries.finders.IVendaFinder;
import com.degloba.ecommerce.vendes.comandes.facade.dtos.ComandaDto;
import com.degloba.events.annotations.EventListener;
import com.degloba.events.annotations.EventListeners;


/**
 * @category Quan un client envia una comanda, crea automàticament un enviament en Estat d'espera.
 * 
 * NOTA: Aquest és un exemple de comunicació a través de múltiples bounded Context
 * utilitzant events. En aquest context, no podem accedir a l'agregat {@link Comanda} directament, 
 * en lloc d'això utilitzem DTO del model de lectura.
 *
 * @author degloba
 * 
 */
@EventListeners
public class ComandaEnviadaPerEnviamentListener {

    @Inject
    private EnviamentsFactory enviamentsFactory;

    @Inject
    private IVendaFinder vendesFinder;

    @Inject
    private IEnviamentsRepository enviamentsRepository;

    @EventListener(asynchronous = true)
    public void handle(ComandaEnviadaEvent event) {
        ComandaDto comandaDto = vendesFinder.find(event.getComandaId());
        Enviament enviament = enviamentsFactory.creaEnviament(comandaDto.getComandaId());
        enviamentsRepository.save(enviament);
    }
}
