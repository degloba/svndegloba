package com.degloba.ecommerce.shipping.application.commands.handlers;

import javax.inject.Inject;

import com.degloba.cqrs.command.annotations.CommandHandlerAnnotation;
import com.degloba.cqrs.command.handler.ICommandHandler;
import com.degloba.ecommerce.shipping.application.commands.SendShipmentCommand;
import com.degloba.ecommerce.shipping.domain.persistence.rdbms.jpa.IShippingRepository;
import com.degloba.ecommerce.shipping.domain.persistence.rdbms.jpa.Shipment;



@CommandHandlerAnnotation
public class ShipOrderCommandHandler implements ICommandHandler<SendShipmentCommand, Void> {

    @Inject
    private IShippingRepository shippingRepository;

    @Override
    public Void handle(SendShipmentCommand command) {
        Shipment shipment = shippingRepository.get(Shipment.class,command.getShipmentId().getId());
        shipment.ship();
        return null;
    }
}
