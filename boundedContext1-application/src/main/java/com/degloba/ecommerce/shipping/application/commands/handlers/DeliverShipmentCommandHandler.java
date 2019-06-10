package com.degloba.ecommerce.shipping.application.commands.handlers;

import javax.inject.Inject;

import com.degloba.cqrs.command.annotations.CommandHandlerAnnotation;
import com.degloba.cqrs.command.handler.ICommandHandler;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.IEnviamentRepository;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.Enviament;
import com.degloba.ecommerce.shipping.application.commands.DeliverShipmentCommand;



@CommandHandlerAnnotation
public class DeliverShipmentCommandHandler implements ICommandHandler<DeliverShipmentCommand, Void> {

    @Inject
    private IEnviamentRepository enviamentRepository;

    @Override
    public Void handle(DeliverShipmentCommand command) {
        Enviament enviament = enviamentRepository.get(Enviament.class,command.getShipmentId());
        enviament.deliver();
        return null;
    }
}
