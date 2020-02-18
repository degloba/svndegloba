package com.degloba.ecommerce.enviaments.cqrs.comandes.handlers;

import javax.inject.Inject;

import com.degloba.cqrs.command.annotations.CommandHandler;
import com.degloba.cqrs.command.handler.ICommandHandler;
import com.degloba.ecommerce.enviaments.cqrs.comandes.commands.EntregarEnviamentCommand;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.Enviament;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.IEnviamentsRepository;


/**
 * @category s'ha rebut un @Command indicant que s'ha de fer un @Enviament
 * 
 * @author degloba
 *
 */
@CommandHandler
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
