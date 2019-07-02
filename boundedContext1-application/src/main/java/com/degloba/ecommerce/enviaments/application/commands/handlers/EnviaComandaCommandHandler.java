package com.degloba.ecommerce.enviaments.application.commands.handlers;

import javax.inject.Inject;

import com.degloba.cqrs.command.annotations.CommandHandlerAnnotation;
import com.degloba.cqrs.command.handler.ICommandHandler;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.IEnviamentsRepository;
import com.degloba.ecommerce.enviaments.application.commands.EntregarEnviamentCommand;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.Enviament;


/**
 * @category s'ha rebut un @Command indicant que s'ha de fer un @Enviament
 * 
 * @author degloba
 *
 */
@CommandHandlerAnnotation
public class EnviaComandaCommandHandler implements ICommandHandler<EntregarEnviamentCommand, Void> {

    @Inject
    private IEnviamentsRepository enviamentsRepository;

    @Override
    public Void handle(EntregarEnviamentCommand command) {
        Enviament enviament = enviamentsRepository.get(Enviament.class,command.getEnviamentId());
        enviament.envia();
        return null;
    }
}
