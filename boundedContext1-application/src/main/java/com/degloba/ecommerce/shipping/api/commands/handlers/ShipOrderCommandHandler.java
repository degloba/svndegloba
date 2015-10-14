package com.degloba.ecommerce.shipping.api.commands.handlers;

import javax.inject.Inject;

import command.annotations.CommandHandlerAnnotation;
import command.handler.CommandHandler;
import com.degloba.ecommerce.shipping.api.commands.SendShipmentCommand;
import com.degloba.ecommerce.shipping.domain.Shipment;
import com.degloba.ecommerce.shipping.domain.IShipmentRepository;

@CommandHandlerAnnotation
public class ShipOrderCommandHandler implements CommandHandler<SendShipmentCommand, Void> {

    @Inject
    private IShipmentRepository repository;

    @Override
    public Void handle(SendShipmentCommand command) {
        Shipment shipment = repository.load(command.getShipmentId());
        shipment.ship();
        return null;
    }
}
