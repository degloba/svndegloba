package com.degloba.ecommerce.enviaments.cqrs.handlers;

import javax.inject.Inject;

import com.degloba.cqrs.command.annotations.CommandHandlerAnnotation;
import com.degloba.cqrs.command.handler.ICommandHandler;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.Enviament;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.IEnviamentsRepository;
import com.degloba.ecommerce.enviaments.cqrs.commands.EntregaEnviamentCommand;


/**
 * @category 
 * 
 * @author degloba
 *
 */
@CommandHandlerAnnotation
public class EntregaEnviamentCommandHandler implements ICommandHandler<EntregaEnviamentCommand, Void> {

    @Inject
    private IEnviamentsRepository enviamentsRepository;

    @Override
    public Void handle(EntregaEnviamentCommand command) {
        Enviament enviament = enviamentsRepository.get(Enviament.class,command.getEnviamentId());
        enviament.entregar();
        return null;
    }
}
