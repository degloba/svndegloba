package com.degloba.ecommerce.shipping.application.listeners;

import javax.inject.Inject;

import com.degloba.ecommerce.enviaments.domain.factories.ShipmentFactory;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.IEnviamentRepository;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.Enviament;
import com.degloba.ecommerce.vendes.cqrs.readmodel.finders.IVendaFinder;
import com.degloba.ecommerce.vendes.domain.events.OrdreEnviadaEvent;
import com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.dtos.OrderDto;
// Event
import com.degloba.event.annotations.EventListeners;
import com.degloba.event.annotations.EventListener;


/**
* Quan un client envia una comanda, crea automàticament un enviament en Estat d'espera.
 * 
 * NOTA: Aquest és un exemple de comunicació a través de múltiples bounded Context
 * utilitzant events. En aquest context, no podem accedir a l'agregat {@link Order} directament, en lloc d'això 
 * utilitzem DTO del model de lectura.
 * 
 */
@EventListeners
public class OrderSubmittedForShippingListener {

    @Inject
    private ShipmentFactory factory;

    @Inject
    private IVendaFinder salesFinder;

    @Inject
    private IEnviamentRepository enviamentRepository;

    @EventListener(asynchronous = true)
    public void handle(OrdreEnviadaEvent event) {
        OrderDto orderDetails = salesFinder.find(event.getOrderId());
        Enviament enviament = factory.createShipment(orderDetails.getOrderId());
        enviamentRepository.save(enviament);
    }
}
