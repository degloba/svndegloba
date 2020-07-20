package com.degloba.ecommerce.enviaments.comandes.cqrs.commands.handlers;

import javax.inject.Inject;

import com.degloba.cqrs.commands.annotations.CommandHandlerAnnotation;
import com.degloba.cqrs.commands.handlers.ICommandHandler;
import com.degloba.ecommerce.enviaments.comandes.cqrs.commands.EntregarEnviamentCommand;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.Enviament;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.IEnviamentsRepository;


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
