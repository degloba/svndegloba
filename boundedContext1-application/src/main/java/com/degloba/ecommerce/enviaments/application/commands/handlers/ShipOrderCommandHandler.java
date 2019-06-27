package com.degloba.ecommerce.enviaments.application.commands.handlers;

import javax.inject.Inject;

import com.degloba.cqrs.command.annotations.CommandHandlerAnnotation;
import com.degloba.cqrs.command.handler.ICommandHandler;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.IEnviamentsRepository;
import com.degloba.ecommerce.enviaments.application.commands.SendShipmentCommand;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.Enviament;



@CommandHandlerAnnotation
public class ShipOrderCommandHandler implements ICommandHandler<SendShipmentCommand, Void> {

    @Inject
    private IEnviamentsRepository enviamentsRepository;

    @Override
    public Void handle(SendShipmentCommand command) {
        Enviament enviament = enviamentsRepository.get(Enviament.class,command.getEnviamentId());
        enviament.ship();
        return null;
    }
}
