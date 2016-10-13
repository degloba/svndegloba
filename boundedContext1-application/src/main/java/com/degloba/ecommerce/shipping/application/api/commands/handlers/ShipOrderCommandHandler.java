package com.degloba.ecommerce.shipping.application.api.commands.handlers;

import javax.inject.Inject;

import com.degloba.cqrs.command.annotations.CommandHandlerAnnotation;
import com.degloba.cqrs.command.handler.CommandHandler;
import com.degloba.ecommerce.shipping.application.api.commands.SendShipmentCommand;
import com.degloba.ecommerce.shipping.domain.Shipment;
import com.degloba.ecommerce.shipping.domain.persistence.rdbms.jpa.IShipmentRepository;


@CommandHandlerAnnotation
public class ShipOrderCommandHandler implements CommandHandler<SendShipmentCommand, Void> {

    @Inject
    private IShipmentRepository repository;

    @Override
    public Void handle(SendShipmentCommand command) {
        Shipment shipment = repository.get(Shipment.class,command.getShipmentId());
        shipment.ship();
        return null;
    }
}
