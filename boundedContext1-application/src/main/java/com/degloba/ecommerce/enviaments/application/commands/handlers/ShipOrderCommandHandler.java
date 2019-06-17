package com.degloba.ecommerce.enviaments.application.commands.handlers;

import javax.inject.Inject;

import com.degloba.cqrs.command.annotations.CommandHandlerAnnotation;
import com.degloba.cqrs.command.handler.ICommandHandler;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.IEnviamentRepository;
import com.degloba.ecommerce.enviaments.application.commands.SendShipmentCommand;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.Enviament;



@CommandHandlerAnnotation
public class ShipOrderCommandHandler implements ICommandHandler<SendShipmentCommand, Void> {

    @Inject
    private IEnviamentRepository enviamentRepository;

    @Override
    public Void handle(SendShipmentCommand command) {
        Enviament enviament = enviamentRepository.get(Enviament.class,command.getShipmentId());
        enviament.ship();
        return null;
    }
}
