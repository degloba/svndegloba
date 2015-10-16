package com.degloba.ecommerce.shipping.application.api.commands.handlers;

import javax.inject.Inject;

import command.annotations.CommandHandlerAnnotation;
import command.handler.CommandHandler;
import com.degloba.ecommerce.shipping.application.api.commands.DeliverShipmentCommand;
import com.degloba.ecommerce.shipping.domain.Shipment;
import com.degloba.ecommerce.shipping.domain.IShipmentRepository;

@CommandHandlerAnnotation
public class DeliverShipmentCommandHandler implements CommandHandler<DeliverShipmentCommand, Void> {

    @Inject
    private IShipmentRepository repository;

    @Override
    public Void handle(DeliverShipmentCommand command) {
        Shipment shipment = repository.load(command.getShipmentId());
        shipment.deliver();
        return null;
    }
}
